package com.izzuddinm.career_core_backend.exception;

public class CustomException extends RuntimeException {

    // A field to store the error code
    private String errorCode;

    // Constructor for CustomException with a message and an error code
    public CustomException(String message, String errorCode) {
        super(message);  // Pass the message to the parent constructor
        this.errorCode = errorCode;
    }

    // Constructor for CustomException with just a message
    public CustomException(String message) {
        super(message);  // Pass the message to the parent constructor
    }

    // Getter for the error code
    public String getErrorCode() {
        return errorCode;
    }

    // Setter for the error code
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
