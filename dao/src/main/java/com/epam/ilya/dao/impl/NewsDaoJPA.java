package com.epam.ilya.dao.impl;

import com.epam.ilya.dao.api.NewsDaoLocal;
import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.News;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * {@inheritDoc}
 *
 * @author Ilya_Bondarenko
 */
@Dependent
public class NewsDaoJPA implements NewsDaoLocal {

    private static final long serialVersionUID = -6786071613749345605L;

    @PersistenceContext
    private EntityManager manager;

    /**
     * {@inheritDoc}
     **/
    @Override
    public News create(News entity) throws DaoException {
        try {
            manager.persist(entity);
        } catch (PersistenceException e) {
            throw new DaoException("Not enough information for persist news", e);
        }
        manager.flush();
        return entity;
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public News findById(Long id) throws DaoException {
        News news = manager.find(News.class, id);
        if (news == null) {
            throw new DaoException("Cannot find news by id" + id);
        }
        return news;
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public News update(News entity) {
        return manager.merge(entity);
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public void delete(News entity) {
        manager.remove(manager.contains(entity) ? entity : manager.merge(entity));
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public List<News> getPaginatedList(int pageNumber, int pageSize) {
        Query query = manager.createQuery("From News", News.class);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public long newsCount() {
        Query queryTotal = manager.createQuery("Select count(news.id) from News news");
        return (long) queryTotal.getSingleResult();
    }
}
