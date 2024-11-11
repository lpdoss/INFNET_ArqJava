package br.edu.infnet.lucas.santos.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.infnet.lucas.santos.client.IEnderecoClient;
import br.edu.infnet.lucas.santos.client.ILocalidadeClient;
import br.edu.infnet.lucas.santos.model.domain.Endereco;
import br.edu.infnet.lucas.santos.model.domain.Estado;
import br.edu.infnet.lucas.santos.model.domain.Municipio;

@Service
public class LocalizacaoService {
	@Autowired
	private IEnderecoClient enderecoClient;
	@Autowired
	private ILocalidadeClient localidadeClient;

	public Endereco findByCep(String cep) {
		return enderecoClient.findByCep(cep);
	}

	public Collection<Estado> obterEstados(){
		return localidadeClient.obterEstados();
	}
	
	public Collection<Municipio> obterMunicipios(Integer uf){
		return localidadeClient.obterMunicipios(uf);
	}
}
