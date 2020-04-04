package com.laundryservice.laundryservice.validator;

import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public   String UserRequestValidator(UserRegistrationRequest userRegistrationRequest){

        if(Strings.isBlank(userRegistrationRequest.getFirstName()))
        {
            return "firstName";
        }

        if(Strings.isBlank(userRegistrationRequest.getLastName()))
        {
            return "lastName";
        }

        if(Strings.isBlank(userRegistrationRequest.getEmail()))
        {
            return "email";
        }

        if(Strings.isBlank(userRegistrationRequest.getPassword()))
        {
            return "password";
        }

        if(Strings.isBlank(userRegistrationRequest.getConfirmPassword()))
        {
            return "confirmPassword";
        }

        if(Strings.isBlank(userRegistrationRequest.getPhoneNumber()))
        {
            return "phone Number";
        }

        if(Strings.isBlank(userRegistrationRequest.getAddress()))
        {
            return "Address";
        }
      return "validate";
    };
}
