package com.laundryservice.laundryservice.validator;

import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import com.laundryservice.laundryservice.exception.PasswordNotEqualException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public   String UserRequestValidator(UserRegistrationRequest userRegistrationRequest){

        if(Strings.isBlank(userRegistrationRequest.getFirstName()))
        {
            return "Provide a valid firstName";
        }

        if(Strings.isBlank(userRegistrationRequest.getLastName()))
        {
            return "Provide a valid lastName";
        }

        if(Strings.isBlank(userRegistrationRequest.getEmail()))
        {
            return "Provide a valid email";
        }

        if(Strings.isBlank(userRegistrationRequest.getPassword()))
        {
            return "Provide a valid password";
        }

        if(Strings.isBlank(userRegistrationRequest.getConfirmPassword()))
        {
            return "Provide a valid confirmPassword";
        }

        if(Strings.isBlank(userRegistrationRequest.getPhoneNumber()))
        {
            return "Provide a valid phone Number";
        }

        if(Strings.isBlank(userRegistrationRequest.getAddress()))
        {
            return "Provide a valid Address";
        }
        if(!userRegistrationRequest.getPassword().equals(userRegistrationRequest.getConfirmPassword()))
        {
          return "Your password and Confirm password are not equal";
        }
      return "validate";
    };
}
