package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.mediaRs.MediaRs;
import org.springframework.http.ResponseEntity;

public interface FindController<T extends MediaRs> {

    ResponseEntity<FindMediaRs> find(String keyword);
    ResponseEntity<T> getByIntegrationID(int id);

}
