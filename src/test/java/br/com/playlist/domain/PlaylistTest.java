package br.com.playlist.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import br.com.playlist.PlaylistApplicationTests;
import br.com.playlist.domain.exceptions.IllegalArgumentException;

public class PlaylistTest extends PlaylistApplicationTests {

	@Test
	public void newPlaylist() {
		
		Playlist playlist = new Playlist(
				new PlaylistId("PLAYLIST-ID"),
				Climate.HOT,
				new HashSet<>(Arrays.asList(new Track("Smooth Criminal"), new Track("Californication"))));
		
		assertEquals(new PlaylistId("PLAYLIST-ID"), playlist.playlistId());
		assertEquals(Climate.HOT, playlist.climate());
		assertEquals(2, playlist.totalTracks());
		
	}
	
	@Test
	public void newPlaylistGivenPlaylistIdIsnullThrowException() {

		assertThatThrownBy(() -> new Playlist(
				null, 
				Climate.HOT,
				new HashSet<>(Arrays.asList(
						new Track("Smooth Criminal"),
						new Track("Californication")))))
				.hasMessage(Playlist.ERR_INVALID_PLAYLIST_ID)
				.isInstanceOf(IllegalArgumentException.class);

	}
	
	@Test
	public void newPlaylistGivenClimateIsnullThrowException() {

		assertThatThrownBy(() -> new Playlist(
				new PlaylistId("PLAYLIST-ID"),
				null,
				new HashSet<>(Arrays.asList(
						new Track("Smooth Criminal"),
						new Track("Californication")))))
				.hasMessage(Playlist.ERR_INVALID_CLIMATE)
				.isInstanceOf(IllegalArgumentException.class);

	}
	
	@Test
	public void newPlaylistGivenTracksIsnullThrowException() {

		assertThatThrownBy(() -> new Playlist(
				new PlaylistId("PLAYLIST-ID"), 
				Climate.HOT,
				null))
				.hasMessage(Playlist.ERR_INVALID_TRACKS)
				.isInstanceOf(IllegalArgumentException.class);

	}
	
}
