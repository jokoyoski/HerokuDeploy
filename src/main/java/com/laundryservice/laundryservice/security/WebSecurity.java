package com.laundryservice.laundryservice.security;


import com.laundryservice.laundryservice.repository.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter {

    private Environment environment;


    @Autowired
    public WebSecurity(Environment environment){
        this.environment=environment;
    }
    @Autowired
    private UserRegistration userRegistration;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    protected void configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf().disable();                                                                                                          //this is how to register our custom auth filter
        httpSecurity.authorizeRequests().antMatchers("/api/user/**").permitAll()
                .anyRequest().authenticated().and().addFilter(getAuthenticationFilter());

    }



    //register the custom filter for the authentication

    private JwtAuthenticationFilter getAuthenticationFilter() throws Exception //this is where we set the filter
    {
        JwtAuthenticationFilter authenticationFilter=new JwtAuthenticationFilter(environment);
        authenticationFilter.setAuthenticationManager(authenticationManager());  //we set the authentication manager to use the efault filter
        authenticationFilter.setFilterProcessesUrl(this.environment.getProperty("login-url")); //setting the default url for our login
        return authenticationFilter;
    }




    @Override    //userServices and password encoder we wish to use
    protected  void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userRegistration).passwordEncoder(bCryptPasswordEncoder);
    }
}