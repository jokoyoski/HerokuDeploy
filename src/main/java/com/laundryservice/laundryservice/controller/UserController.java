package com.laundryservice.laundryservice.controller;

import com.laundryservice.laundryservice.domain.ServerPostResponse;
import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import com.laundryservice.laundryservice.exception.ServerErrorResponse;
import com.laundryservice.laundryservice.repository.UserRegistration;
import com.laundryservice.laundryservice.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserRegistration userRegistration;
    @Autowired
    private RequestValidator requestValidator;


    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest user, BindingResult result) throws Exception {

        var validateRequest=requestValidator.UserRequestValidator(user);

        if(!validateRequest.equals("validate"))
        {
            ServerErrorResponse serverErrorResponse=new ServerErrorResponse(validateRequest + " is required" );

            return new ResponseEntity<ServerErrorResponse>(serverErrorResponse, HttpStatus.BAD_REQUEST);
        }
        String userValue=userRegistration.saveUser(user);

        ServerPostResponse serverErrorResponse=new ServerPostResponse();
         serverErrorResponse.setDescription("User information saved successfully");
        return new ResponseEntity<ServerPostResponse>(serverErrorResponse, HttpStatus.CREATED);

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
