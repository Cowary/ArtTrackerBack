package org.cowary.arttrackerback.dbCase.ranobe;

import org.cowary.arttrackerback.dbCase.PublisherCrud;
import org.cowary.arttrackerback.entity.Publisher;
import org.cowary.arttrackerback.entity.ranobe.RanobePublisher;
import org.cowary.arttrackerback.integration.model.shiki.PublisherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.ranobe.RanobePublisherRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class RanobePublisherCrud {

    @Autowired
    RanobePublisherRepo ranobePublisherRepo;
    @Autowired
    PublisherCrud publisherCrud;

    public void create(long ranobeId, List<PublisherModel> publishers) {
        List<RanobePublisher> ranobePublishers = new ArrayList<>();
        for (PublisherModel publisherModel : publishers) {
            Publisher publisher = publisherCrud.createOrGetByName(publisherModel.getName());
            ranobePublishers.add(new RanobePublisher(ranobeId, publisher.getId()));
        }
        ranobePublisherRepo.saveAll(ranobePublishers);
    }
}
