package br.com.playlist.ports.adapters.resources;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.playlist.application.playlist.PlaylistApplicationService;
import br.com.playlist.application.playlist.PlaylistData;
import br.com.playlist.application.playlist.TrackData;
import br.com.playlist.domain.Climate;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=PlaylistController.class)
public class PlaylistControllerTest {

	@Autowired
	protected MockMvc mockMvc;
	
	@MockBean
	private PlaylistApplicationService service;
	
	@Test
	public void getPlaylistByCity() throws Exception {
		
		given(service.getByCity("Sao Paulo"))
			.willReturn(new PlaylistData(
					Climate.HOT.name(),
					Arrays.asList(
							new TrackData("Amor pra recomeçar"),
							new TrackData("Por você"))));
		
		mockMvc.perform(get("/v1/city")
				.param("name", "Sao Paulo")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.climate").value("HOT"))
				.andExpect(jsonPath("$.tracks[0].name").value("Amor pra recomeçar"))
				.andExpect(jsonPath("$.tracks[1].name").value("Por você"));
		
	}
	
	@Test
	public void getPlaylistByCoordinates() throws Exception {
		
		given(service.getByCoordinates("x", "y"))
			.willReturn(new PlaylistData(
					Climate.HOT.name(),
					Arrays.asList(
							new TrackData("Amor pra recomeçar"),
							new TrackData("Por você"))));
		
		mockMvc.perform(get("/v1/coordinates")
				.param("lat", "x")
				.param("lon", "y")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.climate").value("HOT"))
				.andExpect(jsonPath("$.tracks[0].name").value("Amor pra recomeçar"))
				.andExpect(jsonPath("$.tracks[1].name").value("Por você"));
		
	}
	
}
