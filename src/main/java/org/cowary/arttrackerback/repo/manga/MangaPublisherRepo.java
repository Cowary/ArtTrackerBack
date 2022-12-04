package org.cowary.arttrackerback.repo.manga;

import org.cowary.arttrackerback.entity.manga.MangaPublisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MangaPublisherRepo extends CrudRepository<MangaPublisher, Long> {

    List<MangaPublisher> findByMangaId(long mangaId);
}
