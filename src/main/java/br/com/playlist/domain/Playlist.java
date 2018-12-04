
package br.com.playlist.domain;

import static br.com.playlist.application.Assert.assertArgumentNotNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYLIST")
public class Playlist {

	protected static final String ERR_INVALID_PLAYLIST_ID = "PlaylistId não pode ser nulo.";
	protected static final String ERR_INVALID_CLIMATE = "Clima não pode ser nulo.";
	protected static final String ERR_INVALID_TRACKS = "A lista de tracks não pode ser nulo.";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Embedded
	private PlaylistId playlistId;
	
	@Enumerated(EnumType.STRING)
	private Climate climate;
	
	@ElementCollection
    @CollectionTable(name = "PLAYLIST_TRACKS", joinColumns = @JoinColumn(name = "PLAYLIST_ID"))
    @Column(name = "TRACK")
	private Set<Track> tracks;

	public Playlist(PlaylistId playlistId, Climate climate, Set<Track> tracks) {
		setPlaylistId(playlistId);
		setClimate(climate);
		setTracks(tracks);
	}
	
	@SuppressWarnings("unused")
	private Playlist() {}

	public int totalTracks() {
		return tracks.size();
	}
	
	public PlaylistId playlistId() {
		return playlistId;
	}

	public Climate climate() {
		return climate;
	}

	public Set<Track> tracks() {
		
		if(tracks == null)
			this.tracks = new HashSet<>();
		
		return Collections.unmodifiableSet(tracks);
	}

	private void setPlaylistId(PlaylistId playlistId) {
		assertArgumentNotNull(playlistId, ERR_INVALID_PLAYLIST_ID);
		this.playlistId = playlistId;
	}

	private void setClimate(Climate climate) {
		assertArgumentNotNull(climate, ERR_INVALID_CLIMATE);
		this.climate = climate;
	}

	private void setTracks(Set<Track> tracks) {
		assertArgumentNotNull(tracks, ERR_INVALID_TRACKS);
		this.tracks = tracks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((climate == null) ? 0 : climate.hashCode());
		result = prime * result + ((playlistId == null) ? 0 : playlistId.hashCode());
		result = prime * result + ((tracks == null) ? 0 : tracks.hashCode());
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
		Playlist other = (Playlist) obj;
		if (climate != other.climate)
			return false;
		if (playlistId == null) {
			if (other.playlistId != null)
				return false;
		} else if (!playlistId.equals(other.playlistId))
			return false;
		if (tracks == null) {
			if (other.tracks != null)
				return false;
		} else if (!tracks.equals(other.tracks))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", climate=" + climate + ", tracks=" + tracks + "]";
	}

}
