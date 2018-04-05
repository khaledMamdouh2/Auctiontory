package com.auctiontory.controller;

import java.io.Serializable;
import java.util.List;

public interface IControllerBase<T> extends Serializable {
    List<T> loadAll();

    void save(T domain);

    void update(T domain);

    void delete(T domain);

    T get(Serializable id);
}
