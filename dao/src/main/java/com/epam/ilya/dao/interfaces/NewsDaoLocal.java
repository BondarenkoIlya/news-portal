package com.epam.ilya.dao.interfaces;

import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.News;

import java.util.List;

public interface NewsDaoLocal extends Dao<News> {

    List<News> findAll() throws DaoException;
}
