package br.com.playlist.domain;

public interface ClimateServicePort {

	Climate getClimateByCity(String name);

	Climate getClimateByCoordenates(String lat, String lon);

}
