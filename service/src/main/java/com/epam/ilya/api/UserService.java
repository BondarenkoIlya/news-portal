package com.epam.ilya.api;

import com.epam.ilya.domain.entities.User;
import com.epam.ilya.exception.ServiceException;

import javax.ejb.Local;

@Local
public interface UserService {

    User findByName(String username) throws ServiceException;
}
