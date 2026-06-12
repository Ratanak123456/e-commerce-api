package co.istad.productapi.advisor;

import co.istad.productapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestControllerAdvisor {
    //handle not found issue
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse<?>> handleResourceAlreadyExitException(ResourceAlreadyExistException ex){
        var response = ErrorResponse.builder()
                .message(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.CONTINUE.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse<?>> handleNoSuchElementException (NoSuchElementException noSuchElementException){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(noSuchElementException.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timeStamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<?>> handleMethodNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String , String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message("Provide data is invalid")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(errors)
                        .timeStamp(LocalDateTime.now())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
