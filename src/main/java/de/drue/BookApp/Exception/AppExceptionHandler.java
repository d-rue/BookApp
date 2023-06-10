package de.drue.BookApp.Exception;

import de.drue.BookApp.Model.ReturnError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ReturnError> handleResourceNotFoundException (final ResourceNotFoundException resourceNotFoundException, final HttpServletRequest request){
        return new ResponseEntity<>(ReturnError.builder()
                .message(resourceNotFoundException.getMessage())
                .timestamp(new Date())
                .path(request.getRequestURI())
                .status(HttpStatus.NOT_FOUND)
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceInappropriateException.class)
    public ResponseEntity<ReturnError> handleResourceInappropriateException (final ResourceInappropriateException resourceInappropriateException, final HttpServletRequest request){
        return new ResponseEntity<>(ReturnError.builder()
                .message(resourceInappropriateException.getMessage())
                .timestamp(new Date())
                .path(request.getRequestURI())
                .status(HttpStatus.NOT_FOUND)
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
