package br.com.otto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.otto.entity.Clientes;
import br.com.otto.repository.ClientesRepository;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {

	@Autowired
	private ClientesRepository repo;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity salvarCliente(@Valid  @RequestBody Clientes cliente) {
		return ResponseEntity.ok(repo.save(cliente));
	}
	
	@GetMapping
	public ResponseEntity listarTodosOsClientes() {
		return ResponseEntity.ok(repo.findAll());
	}

	@GetMapping("{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Clientes listarClientes(@PathVariable Integer id) {
		return repo.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));

	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Integer id) {
		repo.findById(id).map((item) -> {
			repo.delete(item);
			return Void.TYPE;
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));

	}

	
	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity atualizarCliente(@PathVariable Integer id, @Valid @RequestBody Clientes clienteAtualizado) {
		return repo.findById(id).map((item) -> {
			clienteAtualizado.setId(item.getId());
			clienteAtualizado.setDataCadastro(item.getDataCadastro());
			return ResponseEntity.ok().body(repo.save(clienteAtualizado));
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

	}
	
}
