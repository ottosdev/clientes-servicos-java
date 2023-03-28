package br.com.otto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otto.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUsername(String username);

	boolean existsByUsername(String username);
}
