package br.edu.infnet.lucas.santos.model.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.Constantes;
import br.edu.infnet.lucas.santos.dtos.OrdemForm;
import br.edu.infnet.lucas.santos.exceptions.InvalidProductListException;
import br.edu.infnet.lucas.santos.exceptions.NaoEncontradoException;
import br.edu.infnet.lucas.santos.model.domain.Cliente;
import br.edu.infnet.lucas.santos.model.domain.OrdemCompra;
import br.edu.infnet.lucas.santos.model.domain.Produto;
import br.edu.infnet.lucas.santos.model.repository.IOrdemRepository;

@Service
public class OrdemService {
	@Autowired
	private IOrdemRepository ordemRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;
	
	public OrdemCompra incluir(OrdemCompra ordem) {
		return ordemRepository.save(ordem);
	}
	public OrdemCompra incluir(OrdemForm ordemDto) {
		OrdemCompra ordem = new OrdemCompra();
		ordem.setData(ordemDto.getData());
		
		//
		Optional<Cliente> cliente = clienteService.buscar(ordemDto.getCliente());
		if (cliente.isEmpty())
			throw new NaoEncontradoException(Constantes.MSG_CLIENTE_NOT_FOUND);
		ordem.setCliente(cliente.get());
		
		//
		List<Produto> produtos = (new ArrayList<Produto>());
		for (Integer codigoProduto: ordemDto.getProdutos())
		{
			Optional<Produto> produto = produtoService.buscar(codigoProduto);
			if (produto.isEmpty())
				throw new InvalidProductListException(Constantes.MSG_ORDEM_LISTA_PRODUTO_INVALIDA);
			if (!produto.get().isEstoque())
				throw new InvalidProductListException(Constantes.MSG_ORDEM_LISTA_PRODUTO_SEM_ESTOQUE);
			produtos.add(produto.get());
		}
		ordem.setProdutos(produtos);
		return ordemRepository.save(ordem);
	}
	
	public Optional<OrdemCompra> buscar(Integer chave) {
		return ordemRepository.findById(chave);
	}
	
	public List<OrdemCompra> buscarPorUsuario(String email) {
		Cliente cliente = clienteService.buscarPorEmail(email);
		System.out.println(cliente);
		if (cliente == null)
			return new ArrayList<OrdemCompra>();
		return ordemRepository.findByClienteId(cliente.getId());
	}
	
	public Collection<OrdemCompra> obterLista() {
		return ordemRepository.findAll();
	}
	
	public void excluir(Integer codigo)
	{
		Optional<OrdemCompra> ordem = ordemRepository.findById(codigo);
		if (ordem.isEmpty())
			throw new NaoEncontradoException(Constantes.MSG_ORDEM_NOT_FOUND);
		ordemRepository.deleteById(codigo);
	}
}
