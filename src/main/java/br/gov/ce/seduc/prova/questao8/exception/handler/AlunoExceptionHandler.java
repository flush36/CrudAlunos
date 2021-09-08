package br.gov.ce.seduc.prova.questao8.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.ce.seduc.prova.questao8.exception.ErrorDTO;

@ControllerAdvice
public class AlunoExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> processValidationError(MethodArgumentNotValidException ex) {
		
		List<String> errors = new ArrayList<String>();
	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	    	errors.add(error.getField() + ": " + error.getDefaultMessage());
	    });

		return new ResponseEntity<>(new ErrorDTO(errors), HttpStatus.NOT_FOUND);
    }

}
 

	
	
	

