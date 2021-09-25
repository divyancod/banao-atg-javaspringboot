package com.banao.task.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationFailed.class)
    public String verificationFailed()
    {
        return "Verification Failed";
    }

    @ResponseBody
    @ExceptionHandler(UserNotFound.class)
    public String userNotFound()
    {
        return "User Not Found Failed";
    }
}
