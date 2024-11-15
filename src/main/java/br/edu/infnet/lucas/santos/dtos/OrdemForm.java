package br.edu.infnet.lucas.santos.dtos;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrdemForm {
	@NotNull(message = "Data é obrigatória")
	private LocalDate data;
    @NotNull(message = "Cliente é obrigatório")
	private Integer cliente;
    @NotNull(message = "Ao menos 1 produto é obrigatório")
    @Size(min=1, message = "Ao menos 1 produto é obrigatório")
    private List<Integer> produtos;
    
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Integer getCliente() {
		return cliente;
	}
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	public List<Integer> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Integer> produtos) {
		this.produtos = produtos;
	}
    
    
}
