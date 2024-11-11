package br.edu.infnet.lucas.santos.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.model.domain.Calcado;
import br.edu.infnet.lucas.santos.model.repository.ICalcadoRepository;

@Service
public class CalcadoService {
	@Autowired
	private ICalcadoRepository calcadoRepository;
	
	public Calcado incluir(Calcado item) {	
		return calcadoRepository.save(item);
	}
	
	public Collection<Calcado> obterLista() {
		return calcadoRepository.findAll();
	}
}
