package br.gov.ce.seduc.prova.questao8.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ErrorDTO {

	private List<String> erros = new ArrayList<String>();
	
	public ErrorDTO(List<String> erros) {
		this.erros.addAll(erros);
	}
}
