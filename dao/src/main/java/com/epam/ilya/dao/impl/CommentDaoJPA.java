package com.epam.ilya.dao.impl;

import com.epam.ilya.dao.api.CommentDaoLocal;
import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.Comment;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Dependent
public class CommentDaoJPA implements CommentDaoLocal {

    private static final long serialVersionUID = 8865967294712055512L;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Comment create(Comment entity) throws DaoException {
        try {
            manager.persist(entity);
        } catch (PersistenceException e) {
            throw new DaoException("Not enough information for persist comment", e);
        }
        manager.flush();
        return entity;
    }

    @Override
    public Comment findById(Long id) {
        return manager.find(Comment.class, id);
    }

    @Override
    public Comment update(Comment entity) {
        return manager.merge(entity);
    }

    @Override
    public void delete(Comment entity) {
        manager.remove(manager.contains(entity) ? entity : manager.merge(entity));
        manager.flush();
    }
}
