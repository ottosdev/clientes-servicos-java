package br.com.otto.controller.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErros {

	@Getter
	private List<String> erros;
	
	public ApiErros(List<String> erros) {
		this.erros = erros;
	}
	

	public ApiErros(String erros) {
		this.erros = Arrays.asList(erros);
	}
}
