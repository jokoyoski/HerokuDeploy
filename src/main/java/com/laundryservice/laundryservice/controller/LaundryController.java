package com.laundryservice.laundryservice.controller;


import com.laundryservice.laundryservice.domain.ServerPostResponse;
import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import com.laundryservice.laundryservice.exception.ServerErrorResponse;
import com.laundryservice.laundryservice.repository.UserRegistration;
import com.laundryservice.laundryservice.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/laundry")
public class LaundryController {

    @Autowired
    private UserRegistration userRegistration;
    @Autowired
    private RequestValidator requestValidator;



    @GetMapping("/cloths")
    public ResponseEntity<?> Cloth() throws Exception {


        return new ResponseEntity<String>("You are getting this because you are authenticated", HttpStatus.CREATED);

    }

   /* @PostMapping("/login")
    public ResponseEntity<?> LoginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) throws Exception {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()

        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= SecurityConstants.TOKEN_PREFIX+jwtTokenProvider.generateToken(authentication) ;
        LoginResponse value=new LoginResponse();
        value.setToken(jwt);

        return new ResponseEntity<Object>(value, HttpStatus.CREATED);
    }*/

}
