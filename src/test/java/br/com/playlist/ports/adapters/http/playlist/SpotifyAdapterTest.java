package br.com.playlist.ports.adapters.http.playlist;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.playlist.PlaylistApplicationTests;
import br.com.playlist.domain.Climate;
import br.com.playlist.domain.Playlist;
import br.com.playlist.domain.PlaylistId;
import br.com.playlist.domain.PlaylistRepository;
import br.com.playlist.ports.adapters.http.playlist.TrackListResource.Track;

public class SpotifyAdapterTest extends PlaylistApplicationTests {

	@Autowired
	@InjectMocks
	private SpotifyAdapter spotifyAdapter;
	
	@MockBean
	private PlaylistRepository repository;
	
	@MockBean(name = "restTemplateSpotify")
	private RestTemplate template;
	
	@MockBean
	private SpotifyAuthorizer spotifyAuthorizer;
	
	@Test
	public void getPlaylistByClimate() {
		
		given(spotifyAuthorizer.getToken())
			.willReturn("abcde");
		
		given(template.exchange(eq("https://api.spotify.com/v1/search?q=classical&type=track&market=BR&limit=10"),
				eq(HttpMethod.GET), 
				any(HttpEntity.class),
				eq(TrackListResource.class)))
			.willReturn(new ResponseEntity<TrackListResource>(
					new TrackListResource(new Track(Arrays.asList(new TrackListResource.Track.Item("Ainda é cedo."))))
					, HttpStatus.OK));

		given(repository.newIdentity())
			.willReturn(new PlaylistId("PLAYLIST-ID"));
		
		given(repository.existsPlaylist(Climate.FROSTY))
			.willReturn(false);
		
		Playlist playlist = spotifyAdapter.getPlaylistByClimate(Climate.FROSTY).get();
		
		assertEquals(Climate.FROSTY, playlist.climate());
		assertEquals(1, playlist.tracks().size());
		
		verify(repository).save(playlist);
		
	}
	
	@Test
	public void getPlaylistByClimateGivenAlreadyExistsPlaylistSoNotSave() {
		
		given(spotifyAuthorizer.getToken())
			.willReturn("abcde");
		
		given(template.exchange(eq("https://api.spotify.com/v1/search?q=classical&type=track&market=BR&limit=10"),
				eq(HttpMethod.GET), 
				any(HttpEntity.class),
				eq(TrackListResource.class)))
			.willReturn(new ResponseEntity<TrackListResource>(
					new TrackListResource(new Track(Arrays.asList(new TrackListResource.Track.Item("Ainda é cedo."))))
					, HttpStatus.OK));

		given(repository.newIdentity())
			.willReturn(new PlaylistId("PLAYLIST-ID"));
		
		given(repository.existsPlaylist(Climate.FROSTY))
			.willReturn(true);
		
		Playlist playlist = spotifyAdapter.getPlaylistByClimate(Climate.FROSTY).get();
		
		assertEquals(Climate.FROSTY, playlist.climate());
		assertEquals(1, playlist.tracks().size());
		
		verify(repository, times(0)).save(playlist);
		
	}
	
	@Test
	public void getPlaylistByClimateGivenSpotifyOfflineAndExistsPlaylistInDataBaseThenReturn() {
		
		Playlist playlist = mock(Playlist.class);
		
		given(playlist.climate())
			.willReturn(Climate.FROSTY);
		
		given(spotifyAuthorizer.getToken())
			.willReturn("abcde");
		
		given(template.exchange(eq("http://www.spotify.com?q=classical&type=track&market=BR&limit=10"),
				eq(HttpMethod.GET), 
				any(HttpEntity.class),
				eq(TrackListResource.class)))
			.willThrow(new HttpServerErrorException(HttpStatus.BAD_GATEWAY));

		given(repository.findByClimate(Climate.FROSTY))
			.willReturn(Optional.of(playlist));
		
		Playlist playlistReturned = spotifyAdapter.getPlaylistByClimate(Climate.FROSTY).get();
		
		assertEquals(Climate.FROSTY, playlistReturned.climate());
		
	}
	
}
