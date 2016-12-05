package com.epam.ilya.dao.impl;

import com.epam.ilya.dao.api.NewsDaoLocal;
import com.epam.ilya.domain.entities.News;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class NewsDaoJPA implements NewsDaoLocal {

    private static final long serialVersionUID = -6786071613749345605L;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<News> findAll() {
        Query query = manager.createQuery("FROM News", News.class);
        return (List<News>) query.getResultList();
    }

    @Override
    public News create(News entity) {
        manager.persist(entity);
        manager.flush();
        return entity;
    }

    @Override
    public News findById(Long id) {
        return manager.find(News.class, id);
    }

    @Override
    public News update(News entity) {
        return manager.merge(entity);
    }

    @Override
    public void delete(News entity) {
        manager.remove(entity);
    }
}
