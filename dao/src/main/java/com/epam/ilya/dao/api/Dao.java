package com.epam.ilya.dao.api;

import com.epam.ilya.dao.exceptions.DaoException;

import java.io.Serializable;

/**
 * Interface declares CRUD operation method
 *
 * @param <T> type of entity
 */
public interface Dao<T> extends Serializable {

    /**
     * Method creates new record in database by entity
     *
     * @param entity to create
     * @return entity with id from database
     * @throws DaoException in case if entity have not got enough information
     */
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
    T update(T entity);

    /**
     * Method deletes correspondent record from base
     *
     * @param entity for deleting
     * @throws DaoException if arise any problem with database
     */
    void delete(T entity);

}
