package com.epam.ilya.entities;

import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.domain.entities.News;
import com.epam.ilya.api.NewsService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@ManagedBean(name = "helloBean", eager = true)
@SessionScoped
public class HelloWorldBean implements Serializable {

    private static final long serialVersionUID = -7022259557065784860L;

    public HelloWorldBean() {
    }

    @Inject
    private NewsService newsService;

    private List<News> newsList;

    public List<News> getNewsList() throws DaoException {
        return newsService.getAllNews();
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
