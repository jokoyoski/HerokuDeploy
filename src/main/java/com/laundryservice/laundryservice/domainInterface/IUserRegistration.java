package com.laundryservice.laundryservice.domainInterface;

import com.laundryservice.laundryservice.domain.User;
import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserRegistration  extends UserDetailsService {

    String saveUser(UserRegistrationRequest userRegistrationRequest);
}
