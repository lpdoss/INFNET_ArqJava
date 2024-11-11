package br.edu.infnet.lucas.santos.model.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.model.domain.OrdemCompra;
import br.edu.infnet.lucas.santos.model.repository.IOrdemRepository;

@Service
public class OrdemService {
	@Autowired
	private IOrdemRepository ordemRepository;
	
	public OrdemCompra incluir(OrdemCompra ordem) {
		return ordemRepository.save(ordem);
	}
	
	public Optional<OrdemCompra> buscar(Integer chave) {
		return ordemRepository.findById(chave);
	}
	public Collection<OrdemCompra> obterLista() {
		return ordemRepository.findAll();
	}
}
