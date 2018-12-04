package br.com.playlist.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Track {

	private String name;

	public Track(String name) {
		setName(name);
	}
	
	@SuppressWarnings("unused")
	private Track() {}

	public String name() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Track other = (Track) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Track [name=" + name + "]";
	}
	
}
