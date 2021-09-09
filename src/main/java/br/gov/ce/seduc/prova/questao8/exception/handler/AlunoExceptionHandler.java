package br.gov.ce.seduc.prova.questao8.exception.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.ce.seduc.prova.questao8.exception.dto.ValidationErrorsDTO;

@ControllerAdvice
public class AlunoExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorsDTO> processValidationError(MethodArgumentNotValidException ex) {
		
		List<String> erros = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
				.collect(Collectors.toList());

		return new ResponseEntity<>(new ValidationErrorsDTO(erros), HttpStatus.BAD_REQUEST);
    }

}
 

	
	
	

