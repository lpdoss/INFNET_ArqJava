package br.edu.infnet.lucas.santos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.lucas.santos.model.domain.*;
import br.edu.infnet.lucas.santos.model.service.CalcadoService;
import br.edu.infnet.lucas.santos.model.service.CasacoService;
import br.edu.infnet.lucas.santos.model.service.ClienteService;
import br.edu.infnet.lucas.santos.model.service.LocalizacaoService;
import br.edu.infnet.lucas.santos.model.service.OrdemService;
import br.edu.infnet.lucas.santos.model.service.ProdutoService;
@Component
public class Loader implements ApplicationRunner {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CasacoService casacoService;
	@Autowired
	private CalcadoService calcadoService;
	@Autowired
	private LocalizacaoService localizacaoService;
	@Autowired
	private OrdemService ordemService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(Estado estado : localizacaoService.obterEstados()) {
			System.out.println("ESTADO: " + estado.getNome());
		}
		
		for(Municipio municipio : localizacaoService.obterMunicipios(33)) {
			System.out.println("MUNICÍPIO: " + municipio.getNome());
		}
		
		
		FileReader file = new FileReader("files/cargaInicial.txt");
		BufferedReader reader = new BufferedReader(file);
		
		boolean eof = false;
		while (!eof)
		{
			var row = reader.readLine();
			if (row == null)
				eof = true;
			else
			{
				if (!row.startsWith("--") && !row.isEmpty())
				{
					var splitRow = row.split(";");
					switch(splitRow[0].toUpperCase())
					{
						case "CL":
							ReadClient(splitRow);
							break;
						case "CA":
							ReadCasaco(splitRow);
							break;
						case "CD":
							ReadCalcado(splitRow);
							break;
						case "OC":
							ReadOrdemCompra(splitRow);
					}
				}
			}
		}		
		reader.close();
	}

	private void ReadCalcado(String[] splitRow) {
		Calcado calcado = new Calcado();
		calcado.setCodigo(Integer.parseInt(splitRow[1]));
		calcado.setDescricao(splitRow[2]);
		calcado.setPreco(Float.parseFloat(splitRow[3]));
		calcado.setEstoque(Boolean.parseBoolean(splitRow[4]));
		calcado.setGenero(splitRow[5]);
		calcado.setProvaDeAgua(Boolean.parseBoolean(splitRow[6]));
		calcado.setTamanho(Integer.parseInt(splitRow[7]));
		calcado.setTipo(splitRow[8]);
		
		calcadoService.incluir(calcado);
		
		System.out.println(calcado);
	}

	private void ReadCasaco(String[] splitRow) {
		Casaco casaco = new Casaco();
		casaco.setCodigo(Integer.parseInt(splitRow[1]));
		casaco.setDescricao(splitRow[2]);
		casaco.setPreco(Float.parseFloat(splitRow[3]));
		casaco.setEstoque(Boolean.parseBoolean(splitRow[4]));
		casaco.setGenero(splitRow[5]);
		casaco.setProvaDeAgua(Boolean.parseBoolean(splitRow[6]));
		casaco.setTamanho(splitRow[7]);
		casaco.setEstilo(splitRow[8]);
		casaco.setFit(splitRow[9]);
		casaco.setPreenchimento(splitRow[10]);
		
		casacoService.incluir(casaco);
		
		System.out.println(casaco);
	}

	private void ReadClient(String[] splitRow) {
		Cliente cliente = new Cliente();
		cliente.setNome(splitRow[1]);
		cliente.setCpf(splitRow[2]);
		cliente.setEmail(splitRow[3]);
		
		Endereco endereco = localizacaoService.findByCep(splitRow[4]);
		cliente.setEndereco(endereco);
		
		clienteService.incluir(cliente);
		
		System.out.println(cliente);
	}

	private void ReadOrdemCompra(String[] splitRow)
	{
		OrdemCompra ordem = new OrdemCompra();
		ordem.setData(LocalDate.parse(splitRow[1]));
		
		Cliente cliente = clienteService.buscarPorEmail(splitRow[2]);
		ordem.setCliente(cliente);
		
		var produtosOrdem = splitRow[3].split(",");
		for(String codigoProdutoString: produtosOrdem)
		{
			int codigoProduto = Integer.parseInt(codigoProdutoString);
			Optional<Produto> produto = produtoService.buscar(codigoProduto);
			if (produto.isPresent())
				ordem.getProdutos().add(produto.get());
		}
		
		ordemService.incluir(ordem);
		System.out.println(ordem);
	}
}
