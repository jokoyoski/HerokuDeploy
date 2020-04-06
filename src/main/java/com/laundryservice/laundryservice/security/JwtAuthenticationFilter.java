package com.laundryservice.laundryservice.security;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laundryservice.laundryservice.domain.User;
import com.laundryservice.laundryservice.domain.UserLoginRequest;
import com.laundryservice.laundryservice.repository.UserRegistration;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private Environment environment;

    @Autowired
    private UserRegistration userRegistration;
    @Autowired
    private DecryptBase64Password decryptBase64Password;

    public JwtAuthenticationFilter(Environment environment){
        this.environment=environment;
    }











    @Override    //this is the method that first gets called
    public Authentication attemptAuthentication(HttpServletRequest servletRequest, HttpServletResponse servletResponse)throws AuthenticationException {
        try {
            var password="";
            UserLoginRequest userSignIn = new ObjectMapper().readValue(servletRequest.getInputStream(), UserLoginRequest.class);
             try{

              password=decrypt(userSignIn.getPassword());
             }
             catch (Exception e)
             {

             }

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(userSignIn.getEmail(),password, new ArrayList<>()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public String decrypt(String password){

        byte[] actualByte= Base64.getDecoder().decode(password);
        String actualString= new String(actualByte);
        return actualString;

    }

    @Override     //this method gets called automatically once the first method above gets called
    public void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain, Authentication authentication) throws IOException , ServletException
    {
        var userModel=((User)authentication.getPrincipal());


        Map<String, Object> claims =new HashMap<>();


        claims.put("id",(Long.toString(userModel.getId())));
        claims.put("username",userModel.getEmail());

        Date now=new Date(System.currentTimeMillis());
        var expire=Long.parseLong(this.environment.getProperty("token-expire"));
        var expireAt= new Date(System.currentTimeMillis()+ expire);
        String userId=Long.toString(userModel.getId());

        String token=  Jwts.builder().setSubject(userId).setClaims(claims).setIssuedAt(now)
                .setExpiration(expireAt).signWith(SignatureAlgorithm.HS256,this.environment.getProperty("SECRET")).compact();
        resp.addHeader("token", token);

    }


}