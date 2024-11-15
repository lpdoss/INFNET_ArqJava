package br.edu.infnet.lucas.santos;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.infnet.lucas.santos.exceptions.DuplicateException;
import br.edu.infnet.lucas.santos.exceptions.InvalidProductListException;
import br.edu.infnet.lucas.santos.exceptions.NaoEncontradoException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private record InvalidatedParams (String cause, String attribute) {}

	@ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        List<InvalidatedParams> validationResponse = errors.stream()
            .map(err -> new InvalidatedParams(err.getMessage(), err.getPropertyPath().toString())
            ).toList();

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request validation failed");
        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("invalidParams", validationResponse);
        return problemDetail;
    }

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<Object> handleValidationExceptions(NaoEncontradoException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
		
		@ExceptionHandler(DuplicateException.class)
		public ResponseEntity<Object> handleDuplicateException(DuplicateException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
		@ExceptionHandler(InvalidProductListException.class)
		public ResponseEntity<Object> handleInvalidProductListException(InvalidProductListException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}	
}
