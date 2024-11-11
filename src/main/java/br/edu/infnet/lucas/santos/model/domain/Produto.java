package br.edu.infnet.lucas.santos.model.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "tblProduto")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produto {
	@Id
	private int codigo;
	
	@NotBlank(message = "A descrição do produto é obrigatória.")
	@Size(max = 50, message = "A descrição deve ter no máximo 50 caracteres.")
	private String descricao;
	@DecimalMin(value = "0.1", message = "O preço deve ser maior que zero.")
	private float preco;
	private boolean estoque;
	@NotNull(message = "Gênero alvo do produto é obrigatório.")
	private String genero;
	@NotNull(message = "Indicação de a prova d'agua é obrigatória.")
	private boolean provaDeAgua;
	
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public boolean isEstoque() {
		return estoque;
	}
	public void setEstoque(boolean estoque) {
		this.estoque = estoque;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public boolean isProvaDeAgua() {
		return provaDeAgua;
	}
	public void setProvaDeAgua(boolean provaDeAgua) {
		this.provaDeAgua = provaDeAgua;
	}
}
