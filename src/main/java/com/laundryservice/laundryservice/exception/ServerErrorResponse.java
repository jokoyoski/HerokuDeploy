package com.laundryservice.laundryservice.exception;

public class ServerErrorResponse {

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage ;

    public ServerErrorResponse( String errorMessage){
        this.errorMessage=errorMessage;
    }

}
