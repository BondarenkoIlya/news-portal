package com.epam.ilya.entities;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.domain.entities.News;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Class for managing and paginating list of news
 *
 * @author Ilya_Bondarenko
 */
@ManagedBean
@SessionScoped
public class ListManager {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 3;
    private static final String NEWS_HOME = "/pages/home?faces-redirect=true";

    @Inject
    private NewsService newsService;

    private int pageNumber;
    private int pageSize;
    private int pageCount;

    /**
     * Method initializes default parameters for pagination
     */
    @PostConstruct
    public void init() {
        this.pageNumber = DEFAULT_PAGE_NUMBER;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * Method connects with service for getting paginated list
     *
     * @return paginated list of news
     */
    public List<News> getPaginatedList() {
        if (pageNumber > pageCount) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        return newsService.getPaginatedList(pageNumber, pageSize);
    }

    /**
     * Method counts news through service
     *
     * @return news amount
     */
    public int getPageCount() {
        this.pageCount = newsService.newsPageCountForPageSize(pageSize);
        return pageCount;
    }

    /**
     * Method change page number on next page
     *
     * @return target URL
     */
    public String nextPage() {
        if (pageNumber != pageCount) {
            this.pageNumber = pageNumber + 1;
        }
        return NEWS_HOME;
    }

    /**
     * Method change page number on previous page
     *
     * @return target URL
     */
    public String previousPage() {
        if (pageNumber != 1) {
            this.pageNumber -= 1;
        }
        return NEWS_HOME;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
