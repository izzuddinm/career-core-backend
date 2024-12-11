package com.izzuddinm.career_core_backend.exception;

import com.izzuddinm.career_core_backend.base.response.BaseResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

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

        // Creating the error response with the exception message
        BaseResponse<String> response = BaseResponse.error("An unexpected error occurred: " + ex.getMessage(), null);

        // Returning the error response with 500 INTERNAL_SERVER_ERROR status
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle validation errors that occur when method arguments are not valid.
     *
     * @param ex the exception that was thrown (MethodArgumentNotValidException)
     * @param request the request that caused the exception
     * @return ResponseEntity with validation error messages and status code
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        // Extracting validation error messages from the exception
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "(" + error.getField() + ": " + error.getDefaultMessage() + ")") // Formatting each error
                .collect(Collectors.joining(", ")); // Joining the error messages with a comma

        // Creating the error response with the validation errors
        BaseResponse<String> response = BaseResponse.error("Validation errors: " +  "[" + errors + "]", null);

        // Returning the error response with 400 BAD_REQUEST status
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle custom exceptions in the application.
     *
     * @param ex the custom exception that was thrown
     * @param request the request that caused the exception
     * @return ResponseEntity with the custom error message and status code
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<String>> handleCustomException(CustomException ex, WebRequest request) {
        // Creating the error response with the custom exception message
        BaseResponse<String> response = BaseResponse.error("Custom error occurred: " + ex.getMessage(), null);

        // Returning the error response with 400 BAD_REQUEST status
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validation errors from @Valid annotations (e.g., in DTO validation).
     *
     * @param ex the validation exception that was thrown (ConstraintViolationException)
     * @param request the request that caused the exception
     * @return ResponseEntity with the validation error messages and status code
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<String>> handleValidationExceptions(ConstraintViolationException ex, WebRequest request) {
        // StringBuilder to accumulate the validation error messages
        StringBuilder errorMessages = new StringBuilder();

        // Loop through each constraint violation and append its message
        ex.getConstraintViolations().forEach(violation -> errorMessages.append(violation.getMessage()).append(" "));

        // Creating the error response with the validation failure messages
        BaseResponse<String> response = BaseResponse.error("Validation failed: " + errorMessages.toString(), null);

        // Returning the error response with 400 BAD_REQUEST status
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
