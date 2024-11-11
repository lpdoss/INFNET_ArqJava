package br.edu.infnet.lucas.santos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.infnet.lucas.santos.model.domain.Endereco;

@FeignClient(url = "https://viacep.com.br/ws", name = "enderecoClient")
public interface IEnderecoClient {

	@RequestMapping(value = "/{cep}/json/")
	Endereco findByCep(@PathVariable String cep);
}