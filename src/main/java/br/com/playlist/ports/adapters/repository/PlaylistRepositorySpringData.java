package br.com.playlist.ports.adapters.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.playlist.domain.Climate;
import br.com.playlist.domain.Playlist;

public interface PlaylistRepositorySpringData extends CrudRepository<Playlist, Long> {

	Optional<Playlist> findByClimate(Climate climate);

}
