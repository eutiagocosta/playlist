package br.com.playlist.application.playlist;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.playlist.domain.Climate;
import br.com.playlist.domain.ClimateServicePort;
import br.com.playlist.domain.Playlist;
import br.com.playlist.domain.PlaylistServicePort;
import br.com.playlist.domain.exceptions.InternalServerError;

@Service
public class PlaylistApplicationService {

	protected static final String ERR_SERVICE_OFFILINE = "Problemas ao obter faixas musicais. Tente novamente mais tarde.";
	
	private ClimateServicePort climateService;
	private PlaylistServicePort playlistService;
	
	public PlaylistApplicationService(ClimateServicePort climateService,
			PlaylistServicePort playlistService) {
		
		this.climateService = climateService;
		this.playlistService = playlistService;
		
	}

	public PlaylistData getByCity(String city) {

		final Climate climate = climateService.getClimateByCity(city);

		return getPlaylistByClimate(climate);

	}

	public PlaylistData getByCoordinates(String lat, String lon) {
		
		final Climate climate = climateService.getClimateByCoordenates(lat, lon);

		return getPlaylistByClimate(climate);
		
	}

	private PlaylistData getPlaylistByClimate(Climate climate) {
		
		Playlist playlist = playlistService.getPlaylistByClimate(climate)
				.orElseThrow(() -> new InternalServerError(ERR_SERVICE_OFFILINE));

		return new PlaylistData(playlist.climate().name(),
				playlist.tracks()
					.stream()
					.map(t -> new TrackData(t.name()))
					.collect(Collectors.toList()));
		
	}

}
