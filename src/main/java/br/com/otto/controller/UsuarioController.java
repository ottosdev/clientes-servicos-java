package br.com.otto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.otto.controller.exception.UsuarioCadastradoException;
import br.com.otto.dto.UsuarioDTO;
import br.com.otto.entity.Usuario;
import br.com.otto.repository.UsuarioRepository;
import br.com.otto.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid UsuarioDTO dto) {
		try {
			
			Usuario u = new Usuario();
			u.setUsername(dto.getUsername());
			u.setPassword(dto.getPassword());
			
			return service.salvar(u);
		} catch (UsuarioCadastradoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}


/*
 * Cliente enviar as credencias 
 * Existem 2 servidores
 * 	Autorizacao
 *  Servidor
 * 
 */