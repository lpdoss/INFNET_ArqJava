package br.edu.infnet.lucas.santos.client;

import java.util.Collection;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.infnet.lucas.santos.model.domain.Estado;
import br.edu.infnet.lucas.santos.model.domain.Municipio;

@FeignClient(url = "https://servicodados.ibge.gov.br/api/v1/localidades", name = "ibge")
public interface ILocalidadeClient {

	@RequestMapping(value = "/estados")
	Collection<Estado> obterEstados();
	
	@RequestMapping(value = "/estados/{uf}/municipios")
	Collection<Municipio> obterMunicipios(@PathVariable Integer uf);
}