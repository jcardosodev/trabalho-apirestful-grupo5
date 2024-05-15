package br.org.serratec.trabalhoGrupo05.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.trabalhoGrupo05.dto.ProdutoDto;
import br.org.serratec.trabalhoGrupo05.dto.ReferenceDto;
import br.org.serratec.trabalhoGrupo05.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDto>> listar() {
		return ResponseEntity.ok(servico.obterProdutos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> listarPorId(@PathVariable Long id) {
		Optional<ProdutoDto> produto = servico.procurarPorId(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/valor/maior")
	public ResponseEntity<List<ProdutoDto>> obterPorValorMaior(@RequestBody String valor) {
		return ResponseEntity.ok(servico.obterValorMaior(Double.valueOf(valor)));
	}
	
	@GetMapping("/categoria")
	public ResponseEntity<List<ProdutoDto>> obterPorCategoria(@RequestBody String categoria) {
		return ResponseEntity.ok(servico.obterCategoria(categoria));
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<ProdutoDto>> obterPorNome(@RequestBody String nome) {
		return ResponseEntity.ok(servico.obterNome(nome));
	}
	
	@GetMapping("/valor/menor")
	public ResponseEntity<List<ProdutoDto>> obterPorValorMenor(@RequestBody String valor) {
		return ResponseEntity.ok(servico.obterValorMenor(Double.valueOf(valor)));
	}
	
	@GetMapping("/valor/entre")
	public ResponseEntity<List<ProdutoDto>> obterValorBetween(@RequestBody ReferenceDto produto) {
		return ResponseEntity.ok(servico.obterValorEntre(produto.valor1(), produto.valor2()));
	}
	
	@GetMapping("valor/crescente")
	public ResponseEntity<List<ProdutoDto>> obterPorValorCrescente() {
		return ResponseEntity.ok(servico.crescenteValor());
	}
	
	@GetMapping("valor_categoria")
	public ResponseEntity<List<ProdutoDto>> obterPorValorOuCategoria(@RequestBody ProdutoDto produto) {
		return ResponseEntity.ok(servico.obterValorOuCategoria(produto.valor(), produto.categoria()));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDto cadastrarProduto (@Valid @RequestBody ProdutoDto produto) {
		return servico.inserirProduto(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> alterarProdutoId(@PathVariable Long id, @RequestBody ProdutoDto produtoAlterado) {
		Optional<ProdutoDto> produto = servico.atualizarProduto(id, produtoAlterado);
		if(produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
		if(!servico.excluirProduto(id)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
}
