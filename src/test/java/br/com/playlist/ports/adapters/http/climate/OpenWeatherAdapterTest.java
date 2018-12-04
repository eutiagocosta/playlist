package br.com.playlist.ports.adapters.http.climate;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.playlist.PlaylistApplicationTests;
import br.com.playlist.domain.Climate;
import br.com.playlist.domain.ClimateServicePort;
import br.com.playlist.ports.adapters.http.climate.OpenWeatherAdapter;
import br.com.playlist.ports.adapters.http.climate.OpenWeatherResource;
import br.com.playlist.ports.adapters.http.climate.OpenWeatherResource.MainResource;

public class OpenWeatherAdapterTest extends PlaylistApplicationTests {

	private ClimateServicePort climateServicePort;
	
	@Mock
	private RestTemplate template;
	
	@Before
	public void setUp() {
		this.climateServicePort = new OpenWeatherAdapter(template, "http://www.globo.com", "openweatherUnit", "openweatherAppid");
	}
	
	@Test
	public void getClimateByCity() {
		
		given(template.getForObject(
				"http://www.globo.com?q=ribeirao preto&units=openweatherUnit&appid=openweatherAppid",
				OpenWeatherResource.class))
			.willReturn(new OpenWeatherResource(new MainResource(35)));

		Climate climate = climateServicePort.getClimateByCity("ribeirao preto");
		
		assertEquals(Climate.HOT, climate);
		
	}
	
	@Test
	public void getClimateByCityGivenAnyProblemReturnDefaultValue() {	
		
		given(template.getForObject(
				"http://www.globo.com?q=ribeirao preto&units=openweatherUnit&appid=openweatherAppid",
				OpenWeatherResource.class))
			.willThrow(new HttpClientErrorException(HttpStatus.BAD_GATEWAY));

		Climate climate = climateServicePort.getClimateByCity("ribeirao preto");
		
		assertEquals(Climate.COOL, climate);
		
	}
	
	@Test
	public void getClimateByCoordenates() {
		
		given(template.getForObject(
				"http://www.globo.com?lat=x&lon=y&units=openweatherUnit&appid=openweatherAppid",
				OpenWeatherResource.class))
			.willReturn(new OpenWeatherResource(new MainResource(35)));
		
		Climate climate = climateServicePort.getClimateByCoordenates("x", "y");
		
		assertEquals(Climate.HOT, climate);
		
	}
	
	@Test
	public void getClimateByCoordenatesGivenAnyProblemReturnDefaultValue() {	
		
		given(template.getForObject(
				"http://www.globo.com?lat=x&lon=y&units=openweatherUnit&appid=openweatherAppid",
				OpenWeatherResource.class))
			.willThrow(new HttpClientErrorException(HttpStatus.BAD_GATEWAY));

		Climate climate = climateServicePort.getClimateByCoordenates("x", "y");
		
		assertEquals(Climate.COOL, climate);
		
	}
	
}
