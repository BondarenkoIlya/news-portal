package com.epam.ilya.entities;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.domain.entities.News;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "newsListBean", eager = true)
@ViewScoped
public class NewsListBean implements Serializable {
    private static final long serialVersionUID = 5238590650882806907L;

    @Inject
    private NewsService newsService;

    public List<News> getNewsList(){
        return newsService.getAllNews();
    }

}
