package com.example.TelecomE_ServicesSystem.exceptions;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse handleNotFound(ResourceNotFoundException ex){ return new com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse(404,"Not Found",ex.getMessage(),new Date()); }

    @ExceptionHandler(BusinessException.class)
    public com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse handleBusiness(BusinessException ex){ return new com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse(400,"Bad Request",ex.getMessage(),new Date()); }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse handleValidation(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldErrors().stream().map(e -> e.getField()+": "+e.getDefaultMessage()).collect(Collectors.joining("; "));
        return new com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse(400,"Bad Request",message,new Date());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse handleData(DataIntegrityViolationException ex){ return new com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse(400,"Bad Request","Database constraint violation: "+(ex.getMostSpecificCause()!=null?ex.getMostSpecificCause().getMessage():ex.getMessage()),new Date()); }

    @ExceptionHandler(Exception.class)
    public com.example.TelecomE_ServicesSystem.exceptions.ErrorResponse handleGeneric(Exception ex){ return new ErrorResponse(500,HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),ex.getMessage(),new Date()); }
}

