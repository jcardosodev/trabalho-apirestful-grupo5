package br.org.serratec.trabalhoGrupo05.dto;

import br.org.serratec.trabalhoGrupo05.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDto(
		Long id,
		@NotBlank(message = "Informe um nome, o campo não pode ser vazio.")
		String nome,
		@NotBlank(message = "Informe uma categoria, não pode ser nula.")
		String categoria,
		@NotNull(message = "Informe um valor, não pode ser nulo.")
		Double valor,
		String descricao) {
	
	public Produto toEntity() {
		return new Produto (this.id, this.nome, this.categoria, this.valor, this.descricao);
	}

}
