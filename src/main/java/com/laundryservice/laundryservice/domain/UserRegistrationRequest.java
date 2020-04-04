package com.laundryservice.laundryservice.domain;

public class UserRegistrationRequest extends User{

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private String confirmPassword;
}
