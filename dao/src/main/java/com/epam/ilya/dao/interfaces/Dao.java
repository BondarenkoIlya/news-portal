package com.epam.ilya.dao.interfaces;

import com.epam.ilya.dao.exceptions.DaoException;

import java.io.Serializable;

public interface Dao<T> extends Serializable {

    T create(T entity) throws DaoException;

    /**
     * Method finds record by id and picks <T> entity
     *
     * @param id for finding <T> object in database
     * @return picked entity
     * @throws DaoException if arise any problem with database
     */
    T findById(Long id) throws DaoException;

    /**
     * Method updates object's record in database or creates new record in case of doesn't find record with same id
     *
     * @param entity object needs to be update
     * @throws DaoException if arise any problem with database
     */
    T update(T entity) throws DaoException;

    /**
     * Method deletes correspondent record from base
     *
     * @param entity for deleting
     * @throws DaoException if arise any problem with database
     */
    void delete(T entity) throws DaoException;

}
