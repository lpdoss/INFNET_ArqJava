package br.edu.infnet.lucas.santos.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tblCalcado")
public class Calcado extends Produto {
	private int tamanho;
	private String tipo;
	
	@Override
	public String toString() {
		return String.format("[%d] Calçado '%s' %s (%s, %d) cadastrado com sucesso", this.getCodigo(), this.getDescricao(), this.getGenero(), tipo, tamanho);
	}
	
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
