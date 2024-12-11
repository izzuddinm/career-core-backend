package com.izzuddinm.career_core_backend.exception;

import com.izzuddinm.career_core_backend.base.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle all types of exceptions globally.
     *
     * @param ex the exception that was thrown
     * @param request the request that caused the exception
     * @return ResponseEntity with an error message and status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<String>> handleAllExceptions(Exception ex, WebRequest request) {
        // Log the exception (optional, depending on your logging setup)
        // For example: log.error("Error occurred: ", ex);
        
        BaseResponse<String> response = BaseResponse.error("An unexpected error occurred: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle custom exceptions (if you have any specific ones).
     *
     * @param ex the custom exception
     * @param request the request that caused the exception
     * @return ResponseEntity with the custom error message and status code
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<String>> handleCustomException(CustomException ex, WebRequest request) {
        BaseResponse<String> response = BaseResponse.error("Custom error occurred: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validation errors (e.g., from @Valid annotations).
     *
     * @param ex the validation exception
     * @param request the request that caused the exception
     * @return ResponseEntity with validation error message and status code
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<String>> handleValidationExceptions(ConstraintViolationException ex, WebRequest request) {
        StringBuilder errorMessages = new StringBuilder();
        ex.getConstraintViolations().forEach(violation -> errorMessages.append(violation.getMessage()).append(" "));
        
        BaseResponse<String> response = BaseResponse.error("Validation failed: " + errorMessages.toString(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
