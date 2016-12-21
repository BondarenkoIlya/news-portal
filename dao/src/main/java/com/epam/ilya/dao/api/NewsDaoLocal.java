package com.epam.ilya.dao.api;

import com.epam.ilya.domain.entities.News;

import java.util.List;

public interface NewsDaoLocal extends Dao<News> {

    List<News> findAll();

    List<News> getPaginatedList(int pageNumber, int pageSize);

    long newsCount();
}
