package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.entity.findRs.FindMediaRs;
import org.springframework.http.ResponseEntity;

public interface FindController {

    ResponseEntity<FindMediaRs> find(String keyword);

}
