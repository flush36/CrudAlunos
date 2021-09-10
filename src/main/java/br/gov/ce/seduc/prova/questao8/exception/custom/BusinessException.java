package br.gov.ce.seduc.prova.questao8.exception.custom;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String error;

	public BusinessException(String error) {
		this.error = error;
	}
	
}
