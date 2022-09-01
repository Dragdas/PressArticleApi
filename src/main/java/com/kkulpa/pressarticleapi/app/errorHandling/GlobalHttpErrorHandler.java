package com.kkulpa.pressarticleapi.app.errorHandling;


import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.ArticleNotFoundException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.AuthorNotFoundException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.IncompleteAuthorInformationException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.InvalidAuthorDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Object> handleTitleNotFoundException(ArticleNotFoundException exception){
        return new ResponseEntity<>("Article with provided ID does not exist in database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<Object> handleAuthorNotFoundException(AuthorNotFoundException exception){
        return new ResponseEntity<>("Author with provided ID does not exist in database", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAuthorDataException.class)
    public ResponseEntity<Object> handleInvalidAuthorData(InvalidAuthorDataException exception){
        String message = "Provided author data does not match database data. " +
                "Try to use id field to access existing author " +
                "or send request without id to create new author";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncompleteAuthorInformationException.class)
    public ResponseEntity<Object> handleIncompleteAuthorInformationException(IncompleteAuthorInformationException exception){
        String message = "Provided author data is incomplete. "+
                "Try to use id field " +
                "or send request without both firstName and lastName fields";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
