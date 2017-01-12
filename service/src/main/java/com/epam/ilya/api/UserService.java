package com.epam.ilya.api;

import com.epam.ilya.domain.entities.User;
import com.epam.ilya.exception.ServiceException;

import javax.ejb.Local;

/**
 * Interface declares all possible operation with user
 *
 * @author Ilya_Bondarenko
 */
@Local
public interface UserService {

    /**
     * Method finds user by username using Dao layer
     *
     * @param username for searching
     * @return user with selected username
     * @throws ServiceException if cannot find user
     */
    User getUserByName(String username) throws ServiceException;
}
