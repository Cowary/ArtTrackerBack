package org.cowary.arttrackerback.rest;

import java.util.List;

public interface GetTitleImpl<T> {

    public List<T> getAllByUsrId(long userId);
    public T getTitle(long titleId);
}
