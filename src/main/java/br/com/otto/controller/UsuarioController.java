package br.com.otto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.otto.dto.UsuarioDTO;
import br.com.otto.entity.Usuario;
import br.com.otto.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repo;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid UsuarioDTO dto) {
		Usuario u = new Usuario();
		u.setUsername(dto.getUsername());
		u.setPassword(dto.getPassword());
		
		return repo.save(u);
	}

}


/*
 * Cliente enviar as credencias 
 * Existem 2 servidores
 * 	Autorizacao
 *  Servidor
 * 
 */