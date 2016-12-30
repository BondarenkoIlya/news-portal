package com.epam.ilya.impl;

import com.epam.ilya.api.UserService;
import com.epam.ilya.dao.api.UserDaoLocal;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl implements UserService {

    private UserDaoLocal userDao;

    @Inject
    public void setUserDao(UserDaoLocal userDao) {
        this.userDao = userDao;
    }
}
