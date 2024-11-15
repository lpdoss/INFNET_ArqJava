package br.edu.infnet.lucas.santos.model.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "tblCliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "Nome do cliente é obrigatório.")
	private String nome;
	private String cpf;
	@NotBlank(message = "Email é obrigatório.")
	@NotNull(message = "Email é obrigatório.")
	@Pattern(regexp = "^(.+)@(\\S+)$", message = "Email invalido")
	private String email;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "idCliente")
	@JsonManagedReference
	@JsonIgnore
	private List<OrdemCompra> ordems;

	public Cliente() {
		this.ordems = new ArrayList<OrdemCompra>();
	}
	public Cliente(Cliente novoCliente) {
		super();
		this.nome = novoCliente.getNome();
		this.cpf = novoCliente.getCpf();
		this.email = novoCliente.getEmail();
		this.endereco = novoCliente.getEndereco();
	}
	
	@Override
	public String toString() {
		return "[" + email + "] Cliente " + nome + " cadastrado com sucesso!";
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
