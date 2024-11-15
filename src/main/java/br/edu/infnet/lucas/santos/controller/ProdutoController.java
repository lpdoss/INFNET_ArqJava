package br.edu.infnet.lucas.santos.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import br.edu.infnet.lucas.santos.Constantes;
import br.edu.infnet.lucas.santos.exceptions.DuplicateException;
import br.edu.infnet.lucas.santos.model.domain.Calcado;
import br.edu.infnet.lucas.santos.model.domain.Casaco;
import br.edu.infnet.lucas.santos.model.domain.Produto;
import br.edu.infnet.lucas.santos.model.service.CalcadoService;
import br.edu.infnet.lucas.santos.model.service.CasacoService;
import br.edu.infnet.lucas.santos.model.service.ProdutoService;

@CrossOrigin()
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CasacoService casacoService;
	@Autowired
	private CalcadoService calcadoService;
	
	@GetMapping
	public Collection<Produto> obterLista() {
		return produtoService.obterLista();
	}
	
	// Endpoint to serve image file
    @RequestMapping(value = "/imagem/{imageName}", method = RequestMethod.GET)
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        // Path to the image file
        Path path = Paths.get("src/main/resources/files/" + imageName);
        // Load the resource
        Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
		}
        // Return ResponseEntity with image content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
    
    @RequestMapping(value = "/incluir/casaco", method = RequestMethod.POST)
	public ResponseEntity<Casaco> IncluirCasaco(@RequestBody Casaco produto)
	{
    	Optional<Produto> dbProduto = produtoService.buscar(produto.getCodigo());
    	if (dbProduto.isPresent())
    		throw new DuplicateException(Constantes.MSG_PRODUTO_EXISTENTE);    		
		Casaco casacoCriado = casacoService.incluir(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(casacoCriado);
	}
    @RequestMapping(value = "/incluir/calcado", method = RequestMethod.POST)
	public ResponseEntity<Calcado> IncluirCalcado(@RequestBody Calcado produto)
	{
    	Optional<Produto> dbProduto = produtoService.buscar(produto.getCodigo());
    	if (dbProduto.isPresent())
    		throw new DuplicateException(Constantes.MSG_PRODUTO_EXISTENTE);
		Calcado calcadoCriado = calcadoService.incluir(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(calcadoCriado);
	}
	
	@DeleteMapping(value = "/{codigo}/excluir")
	public ResponseEntity<String> Delete(@PathVariable Integer codigo)
	{
		produtoService.excluir(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(Constantes.MSG_EXCLUSAO_SUCESSO);
	}
}
