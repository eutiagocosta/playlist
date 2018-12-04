package br.com.playlist.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.playlist.PlaylistApplicationTests;

public class ClimateTest extends PlaylistApplicationTests {

	@Test
	public void getClimateFromValue() {
		
		assertEquals(Climate.FROSTY, Climate.fromValue(0));
		assertEquals(Climate.CHILLY, Climate.fromValue(12));
		assertEquals(Climate.COOL, Climate.fromValue(22));
		assertEquals(Climate.HOT, Climate.fromValue(40));
		
	}
	
}
