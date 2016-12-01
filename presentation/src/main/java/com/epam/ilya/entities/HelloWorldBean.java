package com.epam.ilya.entities;

import com.epam.ilya.dao.exceptions.DaoException;
import com.epam.ilya.dao.interfaces.NewsDaoLocal;
import com.epam.ilya.domain.entities.News;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;


@ManagedBean(name = "helloBean", eager = true)
@SessionScoped
public class HelloWorldBean implements Serializable {

    private static final long serialVersionUID = -7022259557065784860L;

    public HelloWorldBean() {
    }

    @Inject
    private NewsDaoLocal newsDaoLocal;

    private News news;

    public News getNews() throws DaoException {
        return newsDaoLocal.findById(1L);
    }

    public void setNews(News news) {
        this.news = news;
    }
}
