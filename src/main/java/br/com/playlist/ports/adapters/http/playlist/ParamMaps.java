package br.com.playlist.ports.adapters.http.playlist;

import java.util.HashMap;
import java.util.Map;

import br.com.playlist.domain.Climate;

public class ParamMaps {

	private static ParamMaps instance;
	private Map<Climate, String> climateMap;
	
	public ParamMaps() {
		configureClimateMap();
	}

	private void configureClimateMap() {
		put(Climate.CHILLY, "rock");
		put(Climate.COOL, "pop");
		put(Climate.FROSTY, "classical");
		put(Climate.HOT, "party");
	}
	
	private void put(Climate climate, String param){
		getClimateMap().put(climate, param);
	}

	public static String getParam(Climate climate) {
		return getInstance().getClimateMap().get(climate);
	}
	
	private Map<Climate, String> getClimateMap(){
		if (climateMap == null)
			climateMap = new HashMap<Climate, String>();
		return climateMap;
	}
	
	private static ParamMaps getInstance() {
		if (instance == null)
			instance = new ParamMaps();
		return instance;
	}
	
}
