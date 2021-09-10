package br.gov.ce.seduc.prova.questao8.exception.handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.ce.seduc.prova.questao8.exception.custom.BusinessException;
import br.gov.ce.seduc.prova.questao8.exception.dto.CustomErrorResponse;
import br.gov.ce.seduc.prova.questao8.exception.dto.ValidationErrorsDTO;
import javassist.NotFoundException;

@ControllerAdvice
public class AlunoExceptionHandler {

	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private CustomErrorResponse getCustomErrorResponse(String msg, HttpStatus status) {
		return new CustomErrorResponse(msg, fmt.format(LocalDateTime.now()), status.value());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorsDTO> processValidationError(MethodArgumentNotValidException ex) {

		List<String> erros = ex.getBindingResult().getFieldErrors().stream()
				.map(erro -> erro.getField() + ": " + erro.getDefaultMessage()).collect(Collectors.toList());

		return new ResponseEntity<>(new ValidationErrorsDTO(erros), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> NotFoundException(NotFoundException ex) {
		return new ResponseEntity<>(getCustomErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = IncorrectResultSizeDataAccessException.class)
	public ResponseEntity<CustomErrorResponse> IncorrectResultSizeDataAccessException(
			IncorrectResultSizeDataAccessException ex) {
		return new ResponseEntity<>(
				getCustomErrorResponse("Existe mais de um aluno com essa mesma matricula.", HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<CustomErrorResponse> BusinessException(BusinessException ex) {
		return new ResponseEntity<>(getCustomErrorResponse(ex.getError(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}

}
