package com.epam.ilya.dao.api;

import com.epam.ilya.domain.entities.News;

import java.util.List;

/**
 * {@inheritDoc}
 * Expand Dao interfaces methods
 *
 * @author Ilya_Bondarenko
 */
public interface NewsDaoLocal extends Dao<News> {

    /**
     * Method finds all news entities
     *
     * @return all news stored in database
     */
    List<News> findAll();

    /**
     * Method finds all entities in range
     *
     * @param pageNumber of all entities
     * @param pageSize amount of entities on page
     * @return paginated news list
     */
    List<News> getPaginatedList(int pageNumber, int pageSize);

    /**
     * Method counts all news entities in database
     *
     * @return news amount
     */
    long newsCount();
}
