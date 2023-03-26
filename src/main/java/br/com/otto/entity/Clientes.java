package br.com.otto.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Entity
@Table(name = "tb_clientes")
public class Clientes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotBlank(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@Column(nullable = false, length = 11)
	@CPF(message = "{campo.cpf.invalido}")
	@NotNull(message = "{campo.cpf.obrigatorio}")
	private String cpf;
	
	@Column(updatable = false, name = "data_cadastro")
	@JsonFormat(pattern = "yyyyMMdd")
	private LocalDate dataCadastro;
	
	@PrePersist
	public void inicandoData() {
		setDataCadastro(LocalDate.now());
	}
	
}
