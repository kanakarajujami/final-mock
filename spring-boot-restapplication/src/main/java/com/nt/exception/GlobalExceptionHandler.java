package com.nt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

     @ExceptionHandler(exception = NoResourceFoundException.class )
     public ResponseEntity<ErrorDetails> handleNoResourceFoundException(NoResourceFoundException exception,
                                                                        WebRequest webRequest){

         ErrorDetails errorDetails=new ErrorDetails();
         errorDetails.setDate(formattedDate());
         errorDetails.setPath(webRequest.getDescription(false));
         errorDetails.setDescription(exception.getMessage());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);

     }

   @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
       Map<String, String> errors=new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach((error)->{
           errors.put(error.getField(),error.getDefaultMessage());
       });

       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(exception = ArithmeticException.class)
    public ResponseEntity<ErrorDetails> handleAuthenticationException(ArithmeticException exception,WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails();
        errorDetails.setDate(formattedDate());
        errorDetails.setPath(webRequest.getDescription(false));
        errorDetails.setDescription(exception.getMessage());
       return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
    }


     private String formattedDate(){
         LocalDateTime dateTime = LocalDateTime.now();
         DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-mm HH:mm:ss");
         return dateTime.format(dateTimeFormatter);

     }


     //handle internal server exceptions
    @ExceptionHandler(exception = Exception.class)
     public ResponseEntity<ErrorDetails> handleInternalServerExceptions(Exception exception,WebRequest webRequest){
         ErrorDetails errorDetails=new ErrorDetails();
         errorDetails.setDate(formattedDate());
         errorDetails.setPath(webRequest.getDescription(false));
         errorDetails.setDescription(exception.getMessage());
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
     }
}
