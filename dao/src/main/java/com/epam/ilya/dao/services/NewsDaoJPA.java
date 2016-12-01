package com.epam.ilya.dao.services;

import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.dao.interfaces.NewsDaoLocal;
import com.epam.ilya.domain.entities.News;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Default
public class NewsDaoJPA implements NewsDaoLocal {

    private static final long serialVersionUID = -6786071613749345605L;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public News create(News entity) throws DaoException {
        manager.persist(entity);
        manager.flush();
        return entity;
    }

    @Override
    public News findById(Long id) throws DaoException {
        return manager.find(News.class, id);
    }

    @Override
    public News update(News entity) throws DaoException {
        return manager.merge(entity);
    }

    @Override
    public void delete(News entity) throws DaoException {
        manager.remove(entity);
    }
}
