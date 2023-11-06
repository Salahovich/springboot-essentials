package giza.example.springbootessentials.ControllerAdvisors;

import giza.example.springbootessentials.Exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionAdvisors extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EmailFormatException.class,
            NationalIdFormatException.class,
            CourseNameAlreadyExists.class,
            PhoneNumberFormatException.class,
            StudentAlreadyEnrolled.class,
            StudentNotFoundException.class,
            InstructorNotFoundException.class,
            CourseNotFoundException.class,
            StudentAlreadyExistException.class,
            InstructorAlreadyExistException.class,
    })
    public ResponseEntity<Object> handleServiceExceptions(Exception exc, WebRequest request){

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", exc.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        Object DefaultMessageSourceResolvable = null;
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(org.springframework.context.support.DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
