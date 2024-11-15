package br.edu.infnet.lucas.santos.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lucas.santos.Constantes;
import br.edu.infnet.lucas.santos.dtos.OrdemForm;
import br.edu.infnet.lucas.santos.model.domain.OrdemCompra;
import br.edu.infnet.lucas.santos.model.service.OrdemService;
import jakarta.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/ordem")
@Validated
public class OrdemController {
	
	@Autowired
	private OrdemService ordemService;
	
	@GetMapping
	public Collection<OrdemCompra> obterLista() {
		return ordemService.obterLista();
	}
	
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public Collection<OrdemCompra> obterLista1(@PathVariable String email) {
		return ordemService.buscarPorUsuario(email);	
	}
	
	@RequestMapping(value = "/incluir", method = RequestMethod.POST)
	public ResponseEntity<OrdemCompra> Incluir(@Valid @RequestBody OrdemForm ordem)
	{
    	OrdemCompra ordemCriada = ordemService.incluir(ordem);
		return ResponseEntity.status(HttpStatus.CREATED).body(ordemCriada);
	}
	
	@DeleteMapping(value = "/{codigo}/excluir")
	public ResponseEntity<String> Delete(@PathVariable Integer codigo)
	{
		ordemService.excluir(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(Constantes.MSG_EXCLUSAO_SUCESSO);
	}
}
