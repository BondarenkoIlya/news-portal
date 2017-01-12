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

/**
 * Class bean making CRUD operation with comments and news
 *
 * @author Ilya_Bondarenko
 */
@ManagedBean
@RequestScoped
public class NewsManager implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsManager.class);

    private static final long serialVersionUID = -3141474265953868096L;

    private static final String NEWS_VIEW = "/pages/news-view?faces-redirect=true";
    private static final String NEWS_EDIT = "/pages/user/news-edit?faces-redirect=true";
    private static final String HOME = "/pages/home?faces-redirect=true";

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

    /**
     * Method gets news from session and checks it's relevant in database
     * In case of there are no any news, create empty one
     */
    @PostConstruct
    public void init() {
        News sessionNews = (News) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("news");
        if (sessionNews == null) {
            this.news = new News();
        } else {
            try {
                this.news = newsService.findById(sessionNews.getId());
            } catch (ServiceException e) {
                LOGGER.warn("News have been deleted.", e);
            }
        }
    }

    /**
     * Method takes news to be shown, checks it's relevant and puts it in session for redirecting
     *
     * @param news to be shown
     * @return target URL
     */
    public String show(News news) {
        try {
            this.news = newsService.findById(news.getId());
            putNewsInSession(news);
        } catch (ServiceException e) {
            LOGGER.warn("Cannot find news by id " + news.getId(), e);
            return HOME;
        }
        return NEWS_VIEW;
    }

    /**
     * Method takes news to be edited, checks it's relevant and puts it in session for redirecting
     *
     * @param news to be edited
     * @return target URL
     */
    public String editNews(News news) {
        try {
            this.news = newsService.findById(news.getId());
        } catch (ServiceException e) {
            LOGGER.warn("Cannot find news by id " + news.getId(), e);
            return HOME;
        }
        return NEWS_EDIT;
    }

    /**
     * Method saves edited version of the news or create new one
     *
     * @param news to be saved
     * @return target URL
     */
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

    /**
     * Method cleans up session and redirecting on page for adding news
     *
     * @return target URL
     */
    public String addNews() {
        this.news = new News();
        eraseSession();
        return NEWS_EDIT;
    }

    /**
     * Method deletes selected news
     *
     * @param news to be deleted
     * @return target URL
     */
    public String deleteNews(News news) {
        newsService.deleteNews(news);
        eraseSession();
        return HOME;
    }

    /**
     * Method creates new comment for selected news
     *
     * @return target URL
     */
    public String saveComment() {
        try {
            this.news = newsService.createCommentForNews(newComment, news);
            putNewsInSession(news);
        } catch (ServiceException e) {
            LOGGER.warn("Cannot create comment", e);
        }
        return NEWS_VIEW;
    }

    /**
     * Method deletes selected comment
     *
     * @param comment to be deleted
     * @return target URL
     */
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
