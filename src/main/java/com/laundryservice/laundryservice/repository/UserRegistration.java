package com.laundryservice.laundryservice.repository;

import com.laundryservice.laundryservice.domain.User;
import com.laundryservice.laundryservice.domain.UserRegistrationRequest;
import com.laundryservice.laundryservice.domainInterface.IUserRegistration;
import com.laundryservice.laundryservice.exception.DuplicateRecordException;
import com.laundryservice.laundryservice.exception.PasswordNotEqualException;
import com.laundryservice.laundryservice.security.DecryptBase64Password;
import com.laundryservice.laundryservice.sql.CreateUser;
import com.laundryservice.laundryservice.sql.GetUserByEmail;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegistration implements IUserRegistration {
    @Autowired
    private GetUserByEmail getUserByEmail;

    @Autowired
    private DecryptBase64Password decryptBase64Password;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CreateUser createUser;
    @Override
    public String saveUser(UserRegistrationRequest userRegistrationRequest) {
        var userInfo=getUserByEmail.GetUserInfo(userRegistrationRequest.getEmail());

        if(null != userInfo.getEmail())
        {
            throw new DuplicateRecordException("user with the email already exist");
        }



        userRegistrationRequest.setPassword (decryptBase64Password.decrypt(userRegistrationRequest.getPassword()));
        bCryptPasswordEncoder.encode(userRegistrationRequest.getPassword());

        var insertUser=createUser.InsertUser(userRegistrationRequest);

        return insertUser;
    }
}
