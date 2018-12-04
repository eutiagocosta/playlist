package br.com.playlist.domain;

import java.util.Optional;

public interface PlaylistServicePort {

	Optional<Playlist> getPlaylistByClimate(Climate climate);

}
