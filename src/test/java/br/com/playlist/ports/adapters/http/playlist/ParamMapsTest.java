package br.com.playlist.ports.adapters.http.playlist;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.playlist.PlaylistApplicationTests;
import br.com.playlist.domain.Climate;

public class ParamMapsTest extends PlaylistApplicationTests {

	@Test
	public void getParam() {
		
		assertEquals("party", ParamMaps.getParam(Climate.HOT));
		assertEquals("pop", ParamMaps.getParam(Climate.COOL));
		assertEquals("classical", ParamMaps.getParam(Climate.FROSTY));
		assertEquals("rock", ParamMaps.getParam(Climate.CHILLY));
		
	}
	
}
