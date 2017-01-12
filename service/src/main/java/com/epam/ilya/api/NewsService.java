package com.epam.ilya.api;

import com.epam.ilya.domain.entities.Comment;
import com.epam.ilya.domain.entities.News;
import com.epam.ilya.exception.ServiceException;

import java.util.List;

/**
 * Interface declares CRUD operations for news and comments
 *
 * @author Ilya_Bondarenko
 */
public interface NewsService {

    /**
     * Method find news by id using Dao layer
     *
     * @param id of searching news
     * @return found news
     * @throws ServiceException if cannot find news
     */
    News findById(long id) throws ServiceException;

    /**
     * Method deletes news using Dao layer
     *
     * @param news to be deleted
     */
    void deleteNews(News news);

    /**
     * Method updates selected news fields using Dao layer
     *
     * @param news to be updated
     * @return updated news
     * @throws ServiceException if cannot update
     */
    News updateNews(News news) throws ServiceException;

    /**
     * Method creates selected news using Dao layer
     *
     * @param news to be updated
     * @throws ServiceException if cannot create
     */
    void createNews(News news) throws ServiceException;

    /**
     * Method creates selected comment for news using Dao layer
     *
     * @param news    for creating comment
     * @param comment to be created
     * @return updated news
     * @throws ServiceException if cannot create
     */
    News createCommentForNews(Comment comment, News news) throws ServiceException;

    /**
     * Method deletes selected comment from news using Dao layer
     *
     * @param news    for deleting comment
     * @param comment to be deleted
     * @return updated news
     * @throws ServiceException if cannot delete
     */
    News deleteCommentFromNews(Comment comment, News news) throws ServiceException;

    /**
     * Method takes news in rage using Dao layer
     *
     * @param pageNumber of paginated list
     * @param pageSize   amount of news on page
     * @return paginated news list
     */
    List<News> getPaginatedList(int pageNumber, int pageSize);

    /**
     * Method counts news pages of paginated list in case of page size number
     *
     * @param pageSize news on one page
     * @return news count
     */
    int newsPageCountForPageSize(int pageSize);
}
