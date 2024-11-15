package br.edu.infnet.lucas.santos.exceptions;

public class InvalidProductListException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidProductListException(String message) {
		super(message);
	}
}