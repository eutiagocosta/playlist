package br.com.playlist.ports.adapters.repository;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import br.com.playlist.PlaylistApplicationTests;
import br.com.playlist.domain.Climate;
import br.com.playlist.domain.Playlist;
import br.com.playlist.domain.PlaylistRepository;

public class PlaylistRepositoryJpaTest extends PlaylistApplicationTests {

	private PlaylistRepository repository;
	
	@Mock
	private PlaylistRepositorySpringData playlistRepositorySpringData;
	
	@Before
	public void setUp() {
		this.repository = new PlaylistRepositoryJpa(playlistRepositorySpringData);
	}
	
	@Test
	public void save() {
		
		Playlist playlist = mock(Playlist.class);
		
		repository.save(playlist);
		
		verify(playlistRepositorySpringData).save(playlist);
		
	}
	
	@Test
	public void existsPlaylist() {
		
		Playlist playlist = mock(Playlist.class);
		
		given(playlistRepositorySpringData.findByClimate(Climate.CHILLY))
			.willReturn(Optional.of(playlist));
		
		repository.existsPlaylist(Climate.CHILLY);
		
		verify(playlistRepositorySpringData).findByClimate(Climate.CHILLY);
		
	}
	
	@Test
	public void findByClimate() {
		
		repository.findByClimate(Climate.FROSTY);
		
		verify(playlistRepositorySpringData).findByClimate(Climate.FROSTY);
		
	}
	
}
