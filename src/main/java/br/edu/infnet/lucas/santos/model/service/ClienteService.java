package br.edu.infnet.lucas.santos.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.model.domain.Cliente;
import br.edu.infnet.lucas.santos.model.repository.IClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private IClienteRepository clienteRepository;
	
	public Cliente incluir(Cliente cliente) {	
		return clienteRepository.save(cliente);
	}
	
	public Cliente buscarPorEmail(String chave) {
		return clienteRepository.findOneByEmail(chave);
	}
	public Collection<Cliente> obterLista() {
		return clienteRepository.findAll();
	}
}
