package br.edu.infnet.lucas.santos.model.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.Constantes;
import br.edu.infnet.lucas.santos.exceptions.NaoEncontradoException;
import br.edu.infnet.lucas.santos.model.domain.Produto;
import br.edu.infnet.lucas.santos.model.repository.IProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private IProdutoRepository produtoRepository;
	
	public Produto incluir(Produto produto) {
		return produtoRepository.save(produto);
	}
	public Optional<Produto> buscar(Integer chave) {
		return produtoRepository.findById(chave);
	}
	public Collection<Produto> obterLista() {
		return produtoRepository.findAll();
	}
	
	public void excluir(Integer codigo)
	{
		Optional<Produto> produto = produtoRepository.findById(codigo);
		if (produto.isEmpty())
			throw new NaoEncontradoException(Constantes.MSG_PRODUTO_NOT_FOUND);
		produtoRepository.deleteById(codigo);
	}
}
