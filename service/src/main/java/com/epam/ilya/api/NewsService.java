package com.epam.ilya.api;

import com.epam.ilya.domain.entities.Comment;
import com.epam.ilya.domain.entities.News;
import com.epam.ilya.exception.ServiceException;

import java.util.List;

public interface NewsService {
    News findById(long id) throws ServiceException;

    List<News> getAllNews();

    void deleteNews(News news);

    News updateNews(News news) throws ServiceException;

    void createNews(News news) throws ServiceException;

    News createCommentForNews(Comment comment, News news) throws ServiceException;

    News deleteCommentFromNews(Comment comment, News news) throws ServiceException;

    List<News> getPaginatedList(int pageNumber, int pageSize);

    int newsPageCountForPageSize(int pageSize);
}
