package org.cowary.arttrackerback.dbCase;


import org.cowary.arttrackerback.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.PublisherRepo;

@Component
public class PublisherCrud {

    @Autowired
    PublisherRepo publisherRepo;

    public Publisher createOrGetByName(String name) {
        return publisherRepo.findByName(name).orElseGet(() -> {
            Publisher publisher = new Publisher(name);
            publisherRepo.save(publisher);
            return publisher;
        });
    }
}
