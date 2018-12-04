package br.com.playlist.domain;

import java.util.Optional;
import java.util.UUID;

public interface PlaylistRepository {

	default PlaylistId newIdentity() {
        return new PlaylistId(UUID.randomUUID().toString().toUpperCase());
    }
	
	Optional<Playlist> findByClimate(Climate climate);

	void save(Playlist playlist);

	Boolean existsPlaylist(Climate climate);

}
