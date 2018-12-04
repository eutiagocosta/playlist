package br.com.playlist.ports.adapters.http.climate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.playlist.domain.Climate;
import br.com.playlist.domain.ClimateServicePort;
import br.com.playlist.domain.exceptions.IllegalArgumentException;

@Component
public class OpenWeatherAdapter implements ClimateServicePort {

	private static final String ERR_INVALID_CITY = "Cidade informada inválida.";
	
	private RestTemplate template;
	private String openweatherUrl;
	private String openweatherUnit;
	private String openweatherAppid;
	
	public OpenWeatherAdapter(@Qualifier("restTemplateOpenweather") RestTemplate template, 
			@Value("${openweather.url}") String openweatherUrl, 
			@Value("${openweather.unit}") String openweatherUnit,
			@Value("${openweather.appid}") String openweatherAppid) {
		this.template = template;
		this.openweatherUrl = openweatherUrl;
		this.openweatherUnit = openweatherUnit;
		this.openweatherAppid = openweatherAppid;
	}

	@Override
	public Climate getClimateByCity(String city) {
		
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openweatherUrl)
				.queryParam("q", city)
		        .queryParam("units", openweatherUnit)
		        .queryParam("appid", openweatherAppid);
		
		final String url;
		
		try {
			url = URLDecoder.decode(builder.toUriString(), "UTF-8");
		} catch (RestClientException | UnsupportedEncodingException e) {
			throw new IllegalArgumentException(ERR_INVALID_CITY);
		}

		final OpenWeatherResource response;
		
		try {
			response = template.getForObject(url, OpenWeatherResource.class);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			return Climate.COOL;
		}
		
		return Climate.fromValue(response.getMain().getTemp());
		
	}

	@Override
	public Climate getClimateByCoordenates(String lat, String lon) {
		
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openweatherUrl)
				.queryParam("lat", lat)
				.queryParam("lon", lon)
		        .queryParam("units", openweatherUnit)
		        .queryParam("appid", openweatherAppid);

		final OpenWeatherResource response;
		
		try {
			response = template.getForObject(builder.toUriString(), OpenWeatherResource.class);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			return Climate.COOL;
		}
		
		return Climate.fromValue(response.getMain().getTemp());
		
	}

}
