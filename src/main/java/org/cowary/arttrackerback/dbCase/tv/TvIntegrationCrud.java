package org.cowary.arttrackerback.dbCase.tv;

import org.cowary.arttrackerback.repo.tv.TvIntegrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TvIntegrationCrud {

    @Autowired
    TvIntegrationRepo tvIntegrationRepo;

//    public void create(String name, long tvId, long idIntegration) {
//        TvIntegration tvIntegration = new TvIntegration(name, tvId, idIntegration);
//        tvIntegrationRepo.save(tvIntegration);
//    }

//    public TvIntegration findByTvId(long tvId) {
//        return tvIntegrationRepo.findByTvId(tvId).orElse(null);
//    }
}
