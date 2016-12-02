package com.epam.ilya.api;

import com.epam.ilya.domain.entities.Comment;
import com.epam.ilya.domain.entities.News;

import java.util.List;

public interface NewsService {
    News findById(long id);

    List<News> getAllNews();

    void deleteNews(News news);

    News updateNews(News news);

    void createNews(News news);

    void deleteComment(Comment comment);
}
