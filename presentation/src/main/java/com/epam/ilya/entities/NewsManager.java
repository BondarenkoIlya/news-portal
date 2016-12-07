package com.epam.ilya.entities;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.domain.entities.Comment;
import com.epam.ilya.domain.entities.News;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class NewsManager implements Serializable {

    private static final long serialVersionUID = -3141474265953868096L;

    public static final String NEWS_VIEW = "news-view?faces-redirect=true";
    public static final String NEWS_EDIT = "news-edit?faces-redirect=true";

    @Inject
    private NewsService newsService;

    private News news;

    private Comment newComment;

    @Produces
    @Named
    public News getNews() {
        return news;
    }

    @Produces
    @Named
    public Comment getNewComment() {
        return newComment;
    }

    @PostConstruct
    public void init() {
    }

    public List<News> getNewsList() {
        return newsService.getAllNews();
    }

    public String show(News news) {
        this.news = news;
        return NEWS_VIEW;
    }

    public String editNews() {
        return NEWS_EDIT;
    }

    public String saveNews() {
        if (news.getId() == null) {
            newsService.createNews(news);
        }else {
            newsService.updateNews(news);
        }
        return NEWS_VIEW;
    }

    public String addNews(){
        this.news = new News();
        news.setDate(LocalDate.now());//temporary for manual test
        return NEWS_EDIT;
    }

    public String saveComment() {
        newComment.setNews(news);
        newsService.createComment(newComment);
        this.newComment = new Comment();
        return NEWS_VIEW;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setNewComment(Comment newComment) {
        this.newComment = newComment;
    }
}
