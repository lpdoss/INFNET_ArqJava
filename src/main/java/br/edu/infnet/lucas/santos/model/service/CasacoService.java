package br.edu.infnet.lucas.santos.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.model.domain.Casaco;
import br.edu.infnet.lucas.santos.model.repository.ICasacoRepository;

@Service
public class CasacoService {
	@Autowired
	private ICasacoRepository casacoRepository;
	
	public Casaco incluir(Casaco item) {	
		return casacoRepository.save(item);
	}
	
	public Collection<Casaco> obterLista() {
		return casacoRepository.findAll();
	}
}
