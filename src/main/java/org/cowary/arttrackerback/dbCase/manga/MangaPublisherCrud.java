package org.cowary.arttrackerback.dbCase.manga;

import org.cowary.arttrackerback.dbCase.PublisherCrud;
import org.cowary.arttrackerback.entity.Publisher;
import org.cowary.arttrackerback.entity.manga.MangaPublisher;
import org.cowary.arttrackerback.integration.model.shiki.PublisherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.manga.MangaPublisherRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class MangaPublisherCrud {

    @Autowired
    MangaPublisherRepo mangaPublisherRepo;
    @Autowired
    PublisherCrud publisherCrud;

    public void create(long mangaId, List<PublisherModel> publishers) {
        List<MangaPublisher> mangaPublishers = new ArrayList<>();
        for (PublisherModel publisherModel : publishers) {
            Publisher publisher = publisherCrud.createOrGetByName(publisherModel.getName());
            mangaPublishers.add(new MangaPublisher(mangaId, publisher.getId()));
        }
        mangaPublisherRepo.saveAll(mangaPublishers);
    }
}
