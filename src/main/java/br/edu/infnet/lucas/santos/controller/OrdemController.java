package br.edu.infnet.lucas.santos.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lucas.santos.model.domain.OrdemCompra;
import br.edu.infnet.lucas.santos.model.service.OrdemService;

@RestController
public class OrdemController {
	
	@Autowired
	private OrdemService ordemService;
	
	@RequestMapping(value = "/ordem", method = RequestMethod.GET)
	public Collection<OrdemCompra> obterLista() {
		return ordemService.obterLista();
	}
	
}
