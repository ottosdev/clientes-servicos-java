package br.com.otto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {

	@NotBlank(message = "Login obrigatorio")
	private String username;
	
	@NotBlank(message = "Senha obrigatoria")
	private String password;
}
