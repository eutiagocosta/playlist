package br.com.playlist.ports.adapters.http.playlist;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import br.com.playlist.PlaylistApplicationTests;

public class SpotifyAuthorizerTest extends PlaylistApplicationTests {

	private SpotifyAuthorizer spotifyAuthorizer;
	
	@Mock
	private RestTemplate template;
	
	@Before
	public void setUp() {
		spotifyAuthorizer = new SpotifyAuthorizer(template, "http://www.spotify.com");
	}
	
	@Test
	public void getToken() {
		
		given(template.postForObject(eq("http://www.spotify.com"), any(HttpEntity.class), eq(TokenResource.class)))
			.willReturn(new TokenResource("abcde", 3600));
		
		final String token = spotifyAuthorizer.getToken();
		
		assertEquals("abcde", token);
		
	}
	
	@Test
	public void getTokenGivenTokenIsNotNullShouldReturn() {
		
		given(template.postForObject(eq("http://www.spotify.com"), any(HttpEntity.class), eq(TokenResource.class)))
			.willReturn(new TokenResource("abcde", 3600));
		
		String token = spotifyAuthorizer.getToken();
		token = spotifyAuthorizer.getToken();
		token = spotifyAuthorizer.getToken();
		
		assertEquals("abcde", token);
		
		verify(template, times(1)).postForObject(eq("http://www.spotify.com"), any(HttpEntity.class), eq(TokenResource.class));
		
	}
	
	@Test
	public void getTokenGivenTokenIsNotNullButIsExpiredShouldGetNewToken() {
		
		given(template.postForObject(eq("http://www.spotify.com"), any(HttpEntity.class), eq(TokenResource.class)))
			.willReturn(new TokenResource("abcde", -1));
		
		String token = spotifyAuthorizer.getToken();
		token = spotifyAuthorizer.getToken();
		
		assertEquals("abcde", token);
		
		verify(template, times(2)).postForObject(eq("http://www.spotify.com"), any(HttpEntity.class), eq(TokenResource.class));
		
	}
	
}
