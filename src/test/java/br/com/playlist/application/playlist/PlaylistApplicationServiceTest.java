package br.com.playlist.application.playlist;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.playlist.PlaylistApplicationTests;
import br.com.playlist.domain.Climate;
import br.com.playlist.domain.ClimateServicePort;
import br.com.playlist.domain.Playlist;
import br.com.playlist.domain.PlaylistServicePort;
import br.com.playlist.domain.Track;
import br.com.playlist.domain.exceptions.InternalServerError;

public class PlaylistApplicationServiceTest extends PlaylistApplicationTests {

	private PlaylistApplicationService service;
	
	@Mock
	private ClimateServicePort climateServicePort;
	
	@Mock
	private PlaylistServicePort playlistServicePort;
	
	@Before
	public void setUp() {
		this.service = new PlaylistApplicationService(climateServicePort, playlistServicePort);
	}
	
	@Test
	public void getByPlaylistByCity() {
		
		Playlist playlist = mock(Playlist.class);
		
		given(playlist.climate())
			.willReturn(Climate.HOT);
		
		given(playlist.tracks())
			.willReturn(new HashSet<>(Arrays.asList(new Track("Tempo perdido"))));
		
		given(climateServicePort.getClimateByCity("Santos"))
			.willReturn(Climate.HOT);
		
		given(playlistServicePort.getPlaylistByClimate(Climate.HOT))
			.willReturn(Optional.of(playlist));
		
		final PlaylistData playlistData = service.getByCity("Santos");
		
		assertEquals("HOT", playlistData.getClimate());
		assertEquals("Tempo perdido", playlistData.getTracks().get(0).getName());
		
	}
	
	@Test
	public void getByCityGivenPlaylistGivenUnavailableServiceAndDoesNotExistLocallyThrowException() {
		
		given(climateServicePort.getClimateByCity("Santos"))
			.willReturn(Climate.HOT);
		
		given(playlistServicePort.getPlaylistByClimate(Climate.HOT))
			.willReturn(Optional.empty());
		
		assertThatThrownBy(() -> service.getByCity("Santos"))
			.hasMessage(PlaylistApplicationService.ERR_SERVICE_OFFILINE)
			.isInstanceOf(InternalServerError.class);
		
	}
	
	@Test
	public void getByPlaylistByCoordenates() {
		
		Playlist playlist = mock(Playlist.class);
		
		given(playlist.climate())
			.willReturn(Climate.FROSTY);
		
		given(playlist.tracks())
			.willReturn(new HashSet<>(Arrays.asList(new Track("Há tempos"))));
		
		given(climateServicePort.getClimateByCoordenates("x", "y"))
			.willReturn(Climate.FROSTY);
		
		given(playlistServicePort.getPlaylistByClimate(Climate.FROSTY))
			.willReturn(Optional.of(playlist));
		
		final PlaylistData playlistData = service.getByCoordinates("x", "y");
		
		assertEquals("FROSTY", playlistData.getClimate());
		assertEquals("Há tempos", playlistData.getTracks().get(0).getName());
		
	}
	
}
