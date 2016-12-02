package com.epam.ilya.dao.api;

import com.epam.ilya.dao.exceptions.DaoException;

import java.io.Serializable;

public interface Dao<T> extends Serializable {

    T create(T entity);

    /**
     * Method finds record by id and picks <T> entity
     *
     * @param id for finding <T> object in database
     * @return picked entity
     * @throws DaoException if arise any problem with database
     */
    T findById(Long id);

    /**
     * Method updates object's record in database or creates new record in case of doesn't find record with same id
     *
     * @param entity object needs to be update
     * @throws DaoException if arise any problem with database
     */
    T update(T entity);

    /**
     * Method deletes correspondent record from base
     *
     * @param entity for deleting
     * @throws DaoException if arise any problem with database
     */
    void delete(T entity);

}
