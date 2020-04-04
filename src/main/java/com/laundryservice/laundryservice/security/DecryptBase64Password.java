package com.laundryservice.laundryservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class DecryptBase64Password {

    public String decrypt(String password){

        byte[] actualByte= Base64.getDecoder().decode(password);
        String actualString= new String(actualByte);
        return actualString;

    }

}
