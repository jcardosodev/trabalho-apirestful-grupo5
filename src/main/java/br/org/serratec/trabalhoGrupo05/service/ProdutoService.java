package br.org.serratec.trabalhoGrupo05.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.trabalhoGrupo05.dto.ProdutoDto;
import br.org.serratec.trabalhoGrupo05.model.Produto;
import br.org.serratec.trabalhoGrupo05.repository.ProdutoRepositorio;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	public List<ProdutoDto> obterProdutos() {
		return produtoRepositorio.findAll().stream()
				.map(p -> new ProdutoDto(p.getId(), p.getNome(), 
				p.getCategoria(), p.getValor(), p.getDescricao())).toList();
	}
	
	public ProdutoDto inserirProduto(ProdutoDto produto) {
		Produto produtoEntity = produtoRepositorio.save(produto.toEntity());
		return produtoEntity.toDto();
}
	
	
	public Optional<ProdutoDto> atualizarProduto(Long id, ProdutoDto produto) {
		Produto produtoEntity = produto.toEntity();
		if (produtoRepositorio.existsById(id)) {
			produtoEntity.setId(id);
			produtoRepositorio.save(produtoEntity);
			return Optional.of(produtoEntity.toDto());
		}
		
		return Optional.empty();
	}
	
	public boolean excluirProduto(Long id) {
		if(!produtoRepositorio.existsById(id)) {
			return false;
		}
		
		produtoRepositorio.deleteById(id);
		return true;
	}
	
	public Optional<ProdutoDto> procurarPorId(Long id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		if(produto.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(produto.get().toDto());
	}
}
