package br.edu.infnet.lucas.santos.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lucas.santos.model.domain.Produto;
import br.edu.infnet.lucas.santos.model.service.ProdutoService;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value = "/produto", method = RequestMethod.GET)
	public Collection<Produto> obterLista() {
		return produtoService.obterLista();
	}
	
}
