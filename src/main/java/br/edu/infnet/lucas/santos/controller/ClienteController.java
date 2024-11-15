package br.edu.infnet.lucas.santos.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.lucas.santos.Constantes;
import br.edu.infnet.lucas.santos.dtos.LoginForm;
import br.edu.infnet.lucas.santos.model.domain.Cliente;
import br.edu.infnet.lucas.santos.model.service.ClienteService;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin()
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public Collection<Cliente> obterLista() {
		return clienteService.obterLista();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<String> Login(@RequestBody LoginForm loginForm)
	{
		Cliente cliente = clienteService.buscarPorEmail(loginForm.getEmail());
		if (cliente == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constantes.MSG_CLIENTE_NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Cliente> SignUp(@RequestBody Cliente cliente)
	{
		Cliente clienteCriado = clienteService.cadastrar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
	}
	
	@DeleteMapping(value = "/{email}/excluir")
	public ResponseEntity<String> Delete(@PathVariable String email)
	{
		clienteService.excluir(email);
		return ResponseEntity.status(HttpStatus.OK).body(Constantes.MSG_EXCLUSAO_SUCESSO);
	}
}
