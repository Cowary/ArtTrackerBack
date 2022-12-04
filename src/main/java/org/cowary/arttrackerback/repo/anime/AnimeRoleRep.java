package org.cowary.arttrackerback.repo.anime;

import org.cowary.arttrackerback.entity.anime.AnimeRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimeRoleRep extends CrudRepository<AnimeRole, Long> {

    List<AnimeRole> findByAnimeId(int animeId);
}
