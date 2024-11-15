package br.edu.infnet.lucas.santos.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tblOrdem")
public class OrdemCompra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCliente", nullable = false)
	@JsonBackReference
	private Cliente cliente;
	
	@ManyToMany
	@JoinTable(name = "produtosOrdem", joinColumns = @JoinColumn(name = "idOrdem"), inverseJoinColumns = @JoinColumn(name = "idProduto"))
	private List<Produto> produtos;
	
	@NotNull(message = "Data é obrigatória.")
	private LocalDate data; 
	
	public OrdemCompra() {
		this.produtos = new ArrayList<Produto>();
	}
	
	@Override
	public String toString() {
		return "[" + data + "] Ordem para cliente " + cliente.getNome() + " cadastrada com sucesso!";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
