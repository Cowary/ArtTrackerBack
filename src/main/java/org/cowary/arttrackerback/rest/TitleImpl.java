package org.cowary.arttrackerback.rest;

import java.util.List;

public interface TitleImpl<T> {

    public List<T> getAllByUsrId(long userId);
    public T getTitle(long titleId);
    public T postTitle(T title);
    public T putTitle(T title);
    public T deleteTitle(T title);
}
