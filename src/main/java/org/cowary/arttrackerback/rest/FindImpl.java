package org.cowary.arttrackerback.rest;

import java.util.List;

public interface FindImpl<T> {

    public List<T> findByUsrId(long userId);
    public T findTitle(long userId, long titleId);
}
