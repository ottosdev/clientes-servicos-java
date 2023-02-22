package br.com.otto.entity;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_clientes")
public class Clientes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
