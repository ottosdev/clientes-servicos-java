package br.com.otto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otto.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
