package br.com.playlist.application.playlist;

import java.util.List;

public class PlaylistData {

	private String climate;
	private List<TrackData> tracks;

	public PlaylistData(String climate, List<TrackData> tracks) {
		this.climate = climate;
		this.tracks = tracks;
	}

	@SuppressWarnings("unused")
	private PlaylistData() {}

	public String getClimate() {
		return climate;
	}

	public List<TrackData> getTracks() {
		return tracks;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public void setTracks(List<TrackData> tracks) {
		this.tracks = tracks;
	}
	
}
