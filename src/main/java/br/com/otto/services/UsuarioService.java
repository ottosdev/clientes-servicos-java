package br.com.otto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.otto.controller.exception.UsuarioCadastradoException;
import br.com.otto.entity.Usuario;
import br.com.otto.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario u = repo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Login nao encontrado "));
		return User.builder().username(u.getUsername()).password(u.getPassword()).roles("User").build();
	}
	
	
	public Usuario salvar(Usuario usuario) {
		boolean exists = repo.existsByUsername(usuario.getUsername());
		if(exists) {
			throw new UsuarioCadastradoException(usuario.getUsername());
		} else {
			return repo.save(usuario);
		}
		
	}

}
