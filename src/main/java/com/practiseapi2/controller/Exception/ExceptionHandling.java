package com.practiseapi2.controller.Exception;

import com.practiseapi2.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandling {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handling(
            ResourceNotFoundException e
    ){

     return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UpdateIdNotFoundException.class)
    public ResponseEntity<?> handling(
            UpdateIdNotFoundException e,
            WebRequest wRequest
    ){

        ErrorDetails edetails= new ErrorDetails(
                new Date(),
                e.getMessage(),
                wRequest.getDescription(true)
        ) ;

        return new ResponseEntity<>(edetails, HttpStatus.BAD_REQUEST);
    }
}
