package br.edu.infnet.lucas.santos.exceptions;

public class NaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NaoEncontradoException(String message) {
		super(message);
	}
}
