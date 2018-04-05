package com.auctiontory.model.dal;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public interface IDaoBase<T> {

    List<T> loadAll();

    void save(T domain);

    void update(T domain);

    void delete(T domain);

    T get(Serializable id);

    void setEntityManager(EntityManager entityManager);

    EntityManager getEntityManager();

}
