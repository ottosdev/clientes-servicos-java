
package br.com.otto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.otto.controller.exception.ApiErros;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiErros handleValidationErros(MethodArgumentNotValidException ex) {
		BindingResult br = ex.getBindingResult();
		List<String> messagesErros=  br.getAllErrors().stream().map((obj) -> {
			return obj.getDefaultMessage();
		}).collect(Collectors.toList());
		
		ApiErros apiErros = new ApiErros(messagesErros);
		
		return apiErros;
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String messagemError = ex.getReason();
		HttpStatus codigoStatus = (HttpStatus) ex.getStatus();
		
		ApiErros apiErros = new ApiErros(messagemError);
		
		return new ResponseEntity<ApiErros>(apiErros, codigoStatus);  
	}
}
