package br.com.playlist.application.playlist;

public class TrackData {

	private String name;

	public TrackData(String name) {
		this.name = name;
	}
	
	@SuppressWarnings("unused")
	private TrackData() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
