package com.epam.ilya.dao.services;

import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.dao.interfaces.CommentDaoLocal;
import com.epam.ilya.domain.entities.Comment;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Default
public class CommentDaoJPA implements CommentDaoLocal {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Comment create(Comment entity) throws DaoException {
        manager.persist(entity);
        manager.flush();
        return entity;
    }

    @Override
    public Comment findById(Long id) throws DaoException {
        return manager.find(Comment.class, id);
    }

    @Override
    public Comment update(Comment entity) throws DaoException {
        return manager.merge(entity);
    }

    @Override
    public void delete(Comment entity) throws DaoException {
        manager.remove(entity);
    }
}
