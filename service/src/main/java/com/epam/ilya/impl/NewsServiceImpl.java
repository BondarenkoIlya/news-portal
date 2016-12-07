package com.epam.ilya.impl;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.dao.api.CommentDaoLocal;
import com.epam.ilya.dao.api.NewsDaoLocal;
import com.epam.ilya.domain.entities.Comment;
import com.epam.ilya.domain.entities.News;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class NewsServiceImpl implements NewsService {


    private NewsDaoLocal newsDaoLocal;


    private CommentDaoLocal commentDaoLocal;

    @Inject
    public NewsServiceImpl(NewsDaoLocal newsDaoLocal, CommentDaoLocal commentDaoLocal) {
        this.newsDaoLocal = newsDaoLocal;
        this.commentDaoLocal = commentDaoLocal;
    }


    @Override
    public News findById(long id) {
        return newsDaoLocal.findById(id);
    }

    @Override
    public List<News> getAllNews() {
        return newsDaoLocal.findAll();
    }

    @Override
    public void deleteNews(News news) {
        newsDaoLocal.delete(news);
    }

    @Override
    public News updateNews(News news) {
        return newsDaoLocal.update(news);
    }

    @Override
    public void createNews(News news) {
        newsDaoLocal.create(news);
    }

    @Override
    public void createComment(Comment comment) {
        commentDaoLocal.create(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentDaoLocal.delete(comment);
    }
}
