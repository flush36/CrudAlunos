package br.gov.ce.seduc.prova.questao8.exception.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrorsDTO {

	private List<String> erros = new ArrayList<String>();
	
	public ValidationErrorsDTO(List<String> erros) {
		this.erros.addAll(erros);
	}
}
