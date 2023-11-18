package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.entity.findRs.FindMediaRs;
import org.springframework.http.ResponseEntity;

public interface FindController<T> {

    ResponseEntity<FindMediaRs> find(String keyword);
    ResponseEntity<T> getByIntegrationID(int id);
    ResponseEntity<String> getPosterUrl(int id);

}
