package br.com.playlist.ports.adapters.http.playlist;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.playlist.domain.Climate;
import br.com.playlist.domain.Playlist;
import br.com.playlist.domain.PlaylistRepository;
import br.com.playlist.domain.PlaylistServicePort;
import br.com.playlist.domain.Track;

@Component
public class SpotifyAdapter implements PlaylistServicePort {

	private PlaylistRepository repository;
	private RestTemplate template;
	private String searchUrl;
	private SpotifyAuthorizer spotifyAuthorizer;
	
	public SpotifyAdapter(PlaylistRepository repository,
			@Qualifier("restTemplateSpotify") RestTemplate template,
			@Value("${spotify.search.url}") String searchUrl,
			SpotifyAuthorizer spotifyAuthorizer) {
		this.repository = repository;
		this.template = template;
		this.searchUrl = searchUrl;
		this.spotifyAuthorizer = spotifyAuthorizer;
		
	}

	@Override
	@HystrixCommand(fallbackMethod = "getPlaylistByClimateFallback")
	public Optional<Playlist> getPlaylistByClimate(Climate climate) {
		
		final List<String> tracks = executeCall(climate);
		
		Playlist playlist = new Playlist(repository.newIdentity(),
				climate, 
				new HashSet<>(tracks)
					.stream()
					.map(t -> new Track(t))
					.collect(Collectors.toSet()));
		
		if(!repository.existsPlaylist(climate))
			repository.save(playlist);
		
		return Optional.of(playlist);
		
	}
	
	public Optional<Playlist> getPlaylistByClimateFallback(Climate climate) {
		return repository.findByClimate(climate);
	}
	
	private List<String> executeCall(Climate climate) {
		
		final HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization", "Bearer " + spotifyAuthorizer.getToken());
		
		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(searchUrl);
		builder.queryParam("q", ParamMaps.getParam(climate));
		builder.queryParam("type", "track");
		builder.queryParam("market", "BR");
		builder.queryParam("limit", "10");

		final ResponseEntity<TrackListResource> response = template.exchange(builder.toUriString(), HttpMethod.GET, entity, TrackListResource.class);
		
		return response
				.getBody()
				.getTrack()
				.getItems()
				.stream()
				.map(item -> item.getName())
				.collect(Collectors.toList());
		
	}

}
