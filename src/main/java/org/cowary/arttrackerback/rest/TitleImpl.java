package org.cowary.arttrackerback.rest;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TitleImpl<T> {

    List<T> getAllByUsrId(long userId);

    T getTitle(long titleId);

    ResponseEntity<T> postTitle(T title);

    ResponseEntity<T> putTitle(T title);

    ResponseEntity<String> deleteTitle(long id);
}