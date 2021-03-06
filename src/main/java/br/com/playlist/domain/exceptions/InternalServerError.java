package br.com.playlist.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends RuntimeException{

	private static final long serialVersionUID = -8555616357135835046L;

	public InternalServerError(String msg) {
		super(msg);
	}
	
}
