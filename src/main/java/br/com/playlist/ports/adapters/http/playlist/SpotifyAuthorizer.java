package br.com.playlist.ports.adapters.http.playlist;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class SpotifyAuthorizer {
	
	private RestTemplate template;
	private String tokenUrl;
	private TokenResource token;
	
	public SpotifyAuthorizer(@Qualifier("restTemplateSpotify") RestTemplate template,
			@Value("${spotify.token.url}") String tokenUrl) {
		this.template = template;
		this.tokenUrl = tokenUrl;
	}

	public String getToken() {
		
		if (token == null || token.isExpired()) {
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "client_credentials");
	
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	
			this.token = template.postForObject(tokenUrl, request, TokenResource.class);
		
		}
		
		return token.getAccessToken();
		
	}

}
