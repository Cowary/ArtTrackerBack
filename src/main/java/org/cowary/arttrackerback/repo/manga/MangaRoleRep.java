package org.cowary.arttrackerback.repo.manga;

import org.cowary.arttrackerback.entity.manga.MangaRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaRoleRep extends CrudRepository<MangaRole, Long> {

    List<MangaRole> findByMangaId(int mangaId);
}
