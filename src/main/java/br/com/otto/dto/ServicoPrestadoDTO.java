package br.com.otto.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {

	@NotBlank(message = "{campo.descricao.obrigatorio}")
	private String descricao;
	@NotBlank(message = "{campo.preco.obrigatorio}")
	private String preco;
	@NotBlank(message = "{campo.data.obrigatorio}")
	private String data;
	@NotNull(message = "{campo.cliente.obrigatorio}")
	private Integer idCliente;
}
