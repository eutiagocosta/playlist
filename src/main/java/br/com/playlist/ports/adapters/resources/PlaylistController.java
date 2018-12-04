package br.com.playlist.ports.adapters.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.playlist.application.playlist.PlaylistApplicationService;
import br.com.playlist.application.playlist.PlaylistData;

@RestController
@RequestMapping("/v1")
public class PlaylistController {

	@Autowired
	private PlaylistApplicationService service;
	
	@RequestMapping("/city")
	public ResponseEntity<PlaylistData> getPlaylistByCity(@RequestParam(value = "name") String name) {
		return ResponseEntity.ok(service.getByCity(name));
	}
	
	@RequestMapping("/coordinates")
	public ResponseEntity<PlaylistData> getPlaylistByCoordenates(@RequestParam(value = "lat") String lat,
			  @RequestParam(value = "lon") String lon) {
		return ResponseEntity.ok(service.getByCoordinates(lat, lon));
	}
	
}
