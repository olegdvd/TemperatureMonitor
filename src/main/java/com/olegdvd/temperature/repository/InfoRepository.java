package com.olegdvd.temperature.repository;

import java.util.List;

public interface InfoRepository<T> {

    List<T> getAll();
    T findOne(T entity);
    T findById(String id);
    T save(T entity);

}
