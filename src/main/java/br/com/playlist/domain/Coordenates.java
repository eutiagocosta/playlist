package br.com.playlist.domain;

public class Coordenates {

	private String lat;
	private String lon;

	public Coordenates(String lat, String lon) {
		setLat(lat);
		setLon(lon);
	}
	
	@SuppressWarnings("unused")
	private Coordenates() {}

	public String getLat() {
		return lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordenates other = (Coordenates) obj;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordenates [lat=" + lat + ", lon=" + lon + "]";
	}
	
}
