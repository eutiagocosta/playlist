package br.com.playlist.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {

	@Value("${spotify.client.id}")
	private String clientId;
	
	@Value("${spotify.client.secret}")
	private String clientSecret;
	
	@Bean
	public RestTemplate restTemplateOpenweather() {
		return new RestTemplate();
	}
	
	@Bean
	public RestTemplate restTemplateSpotify() {
		
		final RestTemplate template = new RestTemplate();
		
		template.getInterceptors().add(new BasicAuthorizationInterceptor(clientId, clientSecret));
		
		return template;
	}
	
}
