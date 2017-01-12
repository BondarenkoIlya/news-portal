package com.epam.ilya.impl;

import com.epam.ilya.api.UserService;
import com.epam.ilya.dao.api.UserDaoLocal;
import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.User;
import com.epam.ilya.exception.ServiceException;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Implementation of {@link UserService}
 *
 * @author Ilya_Bondarenko
 */
@Stateless
public class UserServiceImpl implements UserService {

    private UserDaoLocal userDao;

    @Inject
    public void setUserDao(UserDaoLocal userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByName(String username) throws ServiceException {
        try {
            return userDao.findByName(username);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find user with username -" + username, e);
        }
    }
}
