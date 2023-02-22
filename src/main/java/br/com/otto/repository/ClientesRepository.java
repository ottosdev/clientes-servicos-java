package br.com.otto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.otto.entity.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

}
