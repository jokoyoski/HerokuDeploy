package com.laundryservice.laundryservice.security;

import io.jsonwebtoken.*;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    Environment environment;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
        super(authenticationManager);
        this.environment=environment;
    }

    @Override
    protected void  doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String authorizationHeader=servletRequest.getHeader("Authorization");

        if(authorizationHeader==null||!authorizationHeader.startsWith("Bearer")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        var authenticationToken=getAuthentication(servletRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){

        String authorizationHeader= request.getHeader("Authorization");
        String token= authorizationHeader.replace("Bearer","");
        String userId="";
        if(authorizationHeader==null){
            return null;
        }
        try{
            Claims claims= Jwts.parser().setSigningKey(this.environment.getProperty("SECRET")).parseClaimsJws(token).getBody();
            userId =(String)claims.get("username");

        }catch(SignatureException e){
            System.out.println("Invalid jwt signature");

        }catch(MalformedJwtException e){
            System.out.println("Invalid jwt token");
        }catch(ExpiredJwtException e){
            System.out.println(" Expired jwt signature");
        }catch(UnsupportedJwtException e){
            System.out.println("Unsupported jwt signature");
        }catch(IllegalArgumentException e){
            System.out.println("Jwt is empty");
        }

        if(userId==null|| userId.equals("") ||userId=="0")
        {
            return null;
        }else{
            return new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());
        }


    }

}