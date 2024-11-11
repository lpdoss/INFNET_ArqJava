package br.edu.infnet.lucas.santos.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tblCasaco")
public class Casaco extends Produto {
	private String tamanho;
	private String preenchimento;
	private String estilo;
	private String fit;
	
	@Override
	public String toString() {
		return String.format("[%d] Casaco '%s' %s (%s, %s, %s, %s) cadastrado com sucesso", this.getCodigo(), this.getDescricao(), this.getGenero(), estilo, fit, tamanho, preenchimento);
	}
	
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getPreenchimento() {
		return preenchimento;
	}
	public void setPreenchimento(String preenchimento) {
		this.preenchimento = preenchimento;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public String getFit() {
		return fit;
	}
	public void setFit(String fit) {
		this.fit = fit;
	}
}
