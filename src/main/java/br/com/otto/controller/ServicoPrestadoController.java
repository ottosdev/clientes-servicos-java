
package br.com.otto.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.otto.dto.ServicoPrestadoDTO;
import br.com.otto.entity.Clientes;
import br.com.otto.entity.ServicoPrestado;
import br.com.otto.repository.ClientesRepository;
import br.com.otto.repository.ServicoPrestadoRepository;
import br.com.otto.util.converterStringBigDecimal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
public class ServicoPrestadoController {

	@Autowired
	private ServicoPrestadoRepository repo;
	
	@Autowired ClientesRepository repoClientes;
	
	@Autowired converterStringBigDecimal bigDecimalConverter;
	
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ServicoPrestado salvar(  @RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		ServicoPrestado sp = new ServicoPrestado();
		Integer idCliente = dto.getIdCliente();
		Clientes cliente =  (repoClientes.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente nao encontrado")));

		

		sp.setDescricao(dto.getDescricao());
		sp.setValor(bigDecimalConverter.converter(dto.getPreco()));
		sp.setData(data);
		sp.setCliente(cliente);
		
		return repo.save(sp);
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required= false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes
			) {
		
		return repo.findByNomeClienteAndMes("%"+nome+"%", mes);
	}
}
