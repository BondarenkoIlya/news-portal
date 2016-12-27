package com.epam.ilya.dao.api;

import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.User;

public interface UserDaoLocal {

    User create(User user) throws DaoException;

    User findByName(String name) throws DaoException;
}
