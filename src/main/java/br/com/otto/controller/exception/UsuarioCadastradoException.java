package br.com.otto.controller.exception;

public class UsuarioCadastradoException extends RuntimeException {
	
	public UsuarioCadastradoException(String login) {
		super("Usuario ja cadastrado para o login " + login);
	}

}
