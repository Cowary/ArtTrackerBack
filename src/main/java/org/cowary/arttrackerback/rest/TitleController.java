package org.cowary.arttrackerback.rest;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TitleController<T> {

    ResponseEntity<List<T>> getAllByUsrId(long userId);

    ResponseEntity<T> getTitle(long titleId);

    ResponseEntity<T> postTitle(T title);

    ResponseEntity<T> putTitle(T title);

    ResponseEntity<String> deleteTitle(long id);

}