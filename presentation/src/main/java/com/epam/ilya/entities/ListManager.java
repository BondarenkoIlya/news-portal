package com.epam.ilya.entities;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.domain.entities.News;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@SessionScoped
public class ListManager {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 3;

    @Inject
    private NewsService newsService;

    private int pageNumber;
    private int pageSize;
    private int pageCount;

    @PostConstruct
    public void init() {
        this.pageNumber = DEFAULT_PAGE_NUMBER;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public List<News> getPaginatedList() {
        if (pageNumber > pageCount) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        return newsService.getPaginatedList(pageNumber, pageSize);
    }

    public int getPageCount() {
        this.pageCount = newsService.newsPageCountForPageSize(pageSize);
        return pageCount;
    }

    public String nextPage() {
        if (pageNumber != pageCount) {
            this.pageNumber = pageNumber + 1;
        }
        return "home?faces-redirect=true";
    }

    public String previousPage() {
        if (pageNumber != 1) {
            this.pageNumber -= 1;
        }
        return "home?faces-redirect=true";
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
