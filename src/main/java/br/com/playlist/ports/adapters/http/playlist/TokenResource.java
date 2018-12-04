package br.com.playlist.ports.adapters.http.playlist;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResource {

	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private Integer expiresIn;
	
	private LocalDateTime expirationTime;

	public TokenResource(String accessToken, Integer expiresIn) {
		setAccessToken(accessToken);
		setExpiresIn(expiresIn);
	}

	@SuppressWarnings("unused")
	private TokenResource() {}
	
	public String getAccessToken() {
		this.expirationTime = LocalDateTime.now().plusSeconds(expiresIn);
		return accessToken;
	}
	
	public boolean isExpired() {
		return expirationTime.isBefore(LocalDateTime.now());
	}

	private void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	private void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

}
