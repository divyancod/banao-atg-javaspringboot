package com.banao.task.Exceptions;

import com.banao.task.Model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(VerificationFailed.class)
    public String verificationFailed() {
        return "Verification Failed";
    }

    @ResponseBody
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorModel> userNotFound() {
        return new ResponseEntity<>(new ErrorModel("error","Invalid Credentials"),HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(NotAdminException.class)
    public ResponseEntity<ErrorModel> notAdmin() {
        return new ResponseEntity<>(new ErrorModel("error","You are not authorized"),HttpStatus.FORBIDDEN);
    }
}
