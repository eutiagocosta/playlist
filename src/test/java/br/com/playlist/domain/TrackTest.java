package br.com.playlist.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.playlist.PlaylistApplicationTests;

public class TrackTest extends PlaylistApplicationTests {

	@Test
	public void newTrack() {
		
		Track track = new Track("Californication");
		
		assertEquals("Californication", track.name());
		
	}
	
}
