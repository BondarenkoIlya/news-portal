package com.epam.ilya.impl;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.dao.api.CommentDaoLocal;
import com.epam.ilya.dao.api.NewsDaoLocal;
import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.Comment;
import com.epam.ilya.domain.entities.News;
import com.epam.ilya.exception.ServiceException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Implements all methods from {@link NewsService}
 *
 * @author Ilya_Bondarenko
 */
@Stateless
public class NewsServiceImpl implements NewsService {

    private NewsDaoLocal newsDaoLocal;

    private CommentDaoLocal commentDaoLocal;

    /**
     * Constructor for injecting dependencies
     *
     * @param newsDaoLocal    interface for working with news's Dao
     * @param commentDaoLocal interface for working with comment's Dao
     */
    @Inject
    public NewsServiceImpl(NewsDaoLocal newsDaoLocal, CommentDaoLocal commentDaoLocal) {
        this.newsDaoLocal = newsDaoLocal;
        this.commentDaoLocal = commentDaoLocal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public News findById(long id) throws ServiceException {
        try {
            return newsDaoLocal.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Cannot find news with id" + id, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNews(News news) {
        newsDaoLocal.delete(news);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public News updateNews(News news) throws ServiceException {
        try {
            newsDaoLocal.findById(news.getId());
        } catch (DaoException e) {
            throw new ServiceException("Cannot update nonexistent news", e);
        }
        return newsDaoLocal.update(news);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createNews(News news) throws ServiceException {
        try {
            newsDaoLocal.create(news);
        } catch (DaoException e) {
            throw new ServiceException("Cannot create news", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public News createCommentForNews(Comment comment, News news) throws ServiceException {
        News newNews;
        try {
            comment.setNewsId(news.getId());
            commentDaoLocal.create(comment);
            newNews = newsDaoLocal.findById(news.getId());
        } catch (DaoException e) {
            throw new ServiceException("Cannot find comment's news. News doesn't exist", e);
        }
        return newNews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public News deleteCommentFromNews(Comment comment, News news) throws ServiceException {
        News updatedNews;
        news.getComments().remove(comment);
        commentDaoLocal.delete(comment);
        try {
            updatedNews = newsDaoLocal.findById(news.getId());
        } catch (DaoException e) {
            throw new ServiceException("Cannot find deleting comment's news", e);
        }
        return updatedNews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<News> getPaginatedList(int pageNumber, int pageSize) {
        return newsDaoLocal.getPaginatedList(pageNumber, pageSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int newsPageCountForPageSize(int pageSize) {
        long countResult = newsDaoLocal.newsCount();
        int pageCount;
        if (countResult % pageSize == 0) {
            pageCount = (int) (countResult / pageSize);
        } else {
            pageCount = (int) ((countResult / pageSize) + 1);
        }
        return pageCount;
    }
}
