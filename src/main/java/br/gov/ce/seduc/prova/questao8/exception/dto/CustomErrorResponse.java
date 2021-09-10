package br.gov.ce.seduc.prova.questao8.exception.dto;

import lombok.Getter;

@Getter
public class CustomErrorResponse {

	private String error;
	private String data;
	private Integer status;
	
	public CustomErrorResponse(String error, String data, Integer status) {
		this.error = error;
		this.data = data;
		this.status = status;
	}
	
}
