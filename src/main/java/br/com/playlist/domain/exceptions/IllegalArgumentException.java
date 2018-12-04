package br.com.playlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends RuntimeException {

	private static final long serialVersionUID = -3407985342132249754L;

	public IllegalArgumentException(String msg) {
		super(msg);
	}
}
