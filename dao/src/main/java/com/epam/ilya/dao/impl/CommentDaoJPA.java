package com.epam.ilya.dao.impl;

import com.epam.ilya.dao.api.CommentDaoLocal;
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
    public Comment create(Comment entity) {
        manager.persist(entity);
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
        manager.remove(entity);
    }
}
