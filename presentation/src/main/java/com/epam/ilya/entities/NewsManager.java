package com.epam.ilya.entities;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.domain.entities.Comment;
import com.epam.ilya.domain.entities.News;
import com.epam.ilya.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@ManagedBean
@RequestScoped
public class NewsManager implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsManager.class);

    private static final long serialVersionUID = -3141474265953868096L;

    public static final String NEWS_VIEW = "news-view?faces-redirect=true";
    public static final String NEWS_EDIT = "news-edit?faces-redirect=true";
    public static final String HOME = "home?faces-redirect=true";

    @Inject
    private NewsService newsService;

    private News news;

    private Comment newComment = new Comment();

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
        News sessionNews = (News) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("news");
        if (sessionNews == null) {
            this.news = new News();
        } else {
            try {
                this.news = newsService.findById(sessionNews.getId());
            } catch (ServiceException e) {
                LOGGER.warn("News have been deleted.",e);
            }
        }
    }

    public List<News> getNewsList() {
        return newsService.getAllNews();
    }

    public String show(News news) {//cope paste , read referrer
        try {
            this.news = newsService.findById(news.getId());
            putNewsInSession(news);
        } catch (ServiceException e) {
            LOGGER.warn("Cannot find news by id " + news.getId(), e);
            return HOME;
        }
        return NEWS_VIEW;
    }

    public String editNews(News news) {
        try {
            this.news = newsService.findById(news.getId());
        } catch (ServiceException e) {
            LOGGER.warn("Cannot find news by id " + news.getId(), e);
            return HOME;
        }
        return NEWS_EDIT;
    }

    public String saveNews(News news) {
        if (news.getId() == null) {
            try {
                newsService.createNews(news);
            } catch (ServiceException e) {
                LOGGER.error("Cannot create new news", e);
            }
        } else {
            try {
                newsService.updateNews(news);
            } catch (ServiceException e) {
                LOGGER.warn("Cannot update news", e);
            }
        }
        putNewsInSession(news);
        return NEWS_VIEW;
    }

    public String addNews() {
        this.news = new News();
        eraseSession();
        return NEWS_EDIT;
    }

    public String deleteNews(News news) {
        newsService.deleteNews(news);
        eraseSession();
        return HOME;
    }

    public String saveComment() {
        try {
            this.news = newsService.createCommentForNews(newComment, news);
            putNewsInSession(news);
        } catch (ServiceException e) {
            LOGGER.warn("Cannot create comment", e);
        }
        return NEWS_VIEW;
    }

    public String deleteComment(Comment comment) {
        try {
            newsService.deleteCommentFromNews(comment, news);
        } catch (ServiceException e) {
            LOGGER.warn("Cannot delete comment", e);
        }

        return NEWS_VIEW;
    }

    private void eraseSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("news");
    }

    private void putNewsInSession(News news) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("news", news);
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setNewComment(Comment newComment) {
        this.newComment = newComment;
    }
}
