package com.Uypiren.jensyardsale.exception;


import org.springframework.http.HttpStatusCode;

public class AppException extends RuntimeException{
    private final HttpStatusCode code;

    public AppException(String message, HttpStatusCode code) {
        super(message);
        this.code = code;
    }




    public HttpStatusCode getCode() {
        return code;
    }
}
