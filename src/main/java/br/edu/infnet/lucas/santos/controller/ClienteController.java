package br.edu.infnet.lucas.santos.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lucas.santos.model.domain.Cliente;
import br.edu.infnet.lucas.santos.model.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public Collection<Cliente> obterLista() {
		return clienteService.obterLista();
	}
	
}
