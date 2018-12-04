package br.com.playlist.ports.adapters.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.playlist.domain.Climate;
import br.com.playlist.domain.Playlist;
import br.com.playlist.domain.PlaylistRepository;

@Repository
public class PlaylistRepositoryJpa implements PlaylistRepository {

	private PlaylistRepositorySpringData repository;
	
	public PlaylistRepositoryJpa(PlaylistRepositorySpringData repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Playlist> findByClimate(Climate climate) {
		return repository.findByClimate(climate);
	}

	@Override
	public void save(Playlist playlist) {
		repository.save(playlist);
	}

	@Override
	public Boolean existsPlaylist(Climate climate) {
		return repository.findByClimate(climate).isPresent();
	}

}
