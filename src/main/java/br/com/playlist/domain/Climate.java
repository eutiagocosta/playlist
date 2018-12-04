package br.com.playlist.domain;

public enum Climate {

	CHILLY,
	HOT,
	FROSTY,
	COOL;

	public static Climate fromValue(Integer temp) {
		
		if (isBeetwen(temp, 10, 14))
			return CHILLY;
		if (isBeetwen(temp, 15, 30))
			return COOL;
		if (temp > 30)
			return HOT;
		
		return Climate.FROSTY;
	}
	
	private static Boolean isBeetwen(int value, int menor, int maior) {
		return menor <= value && value <= maior;
	}
	
}
