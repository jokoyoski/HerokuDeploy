package com.laundryservice.laundryservice.domainInterface;

import com.laundryservice.laundryservice.domain.User;
import com.laundryservice.laundryservice.domain.UserRegistrationRequest;

public interface IUserRegistration {

    String saveUser(UserRegistrationRequest userRegistrationRequest);
}
