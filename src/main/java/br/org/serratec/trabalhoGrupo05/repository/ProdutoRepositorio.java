package br.org.serratec.trabalhoGrupo05.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.trabalhoGrupo05.model.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
	
	List<Produto> findByValorGreaterThan(Double valor);
	List<Produto> findByCategoriaContainingIgnoreCase(String categoria);
	List<Produto> findByNomeContainingIgnoreCase(String nome);
}
