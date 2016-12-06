package com.epam.ilya.entities;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.domain.entities.News;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class NewsManager implements Serializable {

    private static final long serialVersionUID = -3141474265953868096L;

    public static final String NEWS_VIEW = "news-view?faces-redirect=true";
    public static final String NEWS_EDIT = "news-edit?faces-redirect=true";

    @Inject
    private NewsService newsService;

    private News news;

    @Produces
    @Named
    @RequestScoped
    public News getNews() {
        return news;
    }

    @PostConstruct
    public void init() {
        this.news = (News) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("news");
        if (news == null) {
            this.news = new News();
        }
    }

    public List<News> getNewsList() {
        return newsService.getAllNews();
    }

    public String show(News news) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().putNow("news", news);
        return NEWS_VIEW;
    }

    public String editNews(News news) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().putNow("news", news);
        return NEWS_EDIT;
    }

    public String saveNews(News news) {

        return NEWS_VIEW;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
