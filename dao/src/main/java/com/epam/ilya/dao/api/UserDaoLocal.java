package com.epam.ilya.dao.api;

import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.User;

/**
 * Interface declares methods necessary for working with user
 *
 * @author Ilya_Bondarenko
 */
public interface UserDaoLocal {

    /**
     * Method finds all user entities in database and chose first
     *
     * @param name of user
     * @return found user
     * @throws DaoException if have no any matches
     */
    User findByName(String name) throws DaoException;
}
