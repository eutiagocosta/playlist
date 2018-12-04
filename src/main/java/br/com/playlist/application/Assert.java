package br.com.playlist.application;

import br.com.playlist.domain.exceptions.IllegalArgumentException;

public class Assert {

	public static void assertArgumentNotEmpty(String field, String errMessage) {
        if (field == null || field.isEmpty())
            throw new IllegalArgumentException(errMessage);
    }
	
	public static void assertArgumentNotNull(Object obj, String errMessage) {
        if (obj == null)
            throw new IllegalArgumentException(errMessage);
    }
	
}
