package br.edu.infnet.lucas.santos.model.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.Constantes;
import br.edu.infnet.lucas.santos.exceptions.DuplicateException;
import br.edu.infnet.lucas.santos.exceptions.NaoEncontradoException;
import br.edu.infnet.lucas.santos.model.domain.Cliente;
import br.edu.infnet.lucas.santos.model.repository.IClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private IClienteRepository clienteRepository;
	
	public Cliente incluir(Cliente cliente) {	
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> buscar(Integer chave)
	{
		return clienteRepository.findById(chave);
	}
	public Cliente buscarPorEmail(String chave) {
		return clienteRepository.findOneByEmail(chave);
	}
	public Collection<Cliente> obterLista() {
		return clienteRepository.findAll();
	}
	
	public Cliente cadastrar(Cliente novoCliente)
	{
		Cliente cliente = clienteRepository.findOneByEmail(novoCliente.getEmail());
		if (cliente != null)
			throw new DuplicateException(Constantes.MSG_CLIENTE_EXISTENTE);
		cliente = new Cliente(novoCliente);
		return clienteRepository.save(cliente);
	}
	
	public void excluir(String email)
	{
		Cliente cliente = clienteRepository.findOneByEmail(email);
		if (cliente == null)
			throw new NaoEncontradoException(Constantes.MSG_CLIENTE_NOT_FOUND);
		clienteRepository.delete(cliente);
	}
}
