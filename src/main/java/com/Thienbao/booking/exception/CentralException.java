package com.Thienbao.booking.exception;

import com.Thienbao.booking.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralException {

    @ExceptionHandler({RuntimeException.class, MethodArgumentNotValidException.class, NotFoundException.class, UserAlreadyExistsException.class, SaveFileException.class})
    public ResponseEntity<?> handleException(Exception e){
        BaseResponse baseResponse = new BaseResponse();

        if (e instanceof NotFoundException){
            baseResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        }else if (e instanceof SaveFileException){
            baseResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        }else{
            baseResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        baseResponse.setData("");
        baseResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);

    };
}
