package com.epam.ilya.entities;

import com.epam.ilya.api.NewsService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name = "newsBean", eager = true)
@ViewScoped
public class NewsBean implements Serializable {

    private static final long serialVersionUID = -2109751677057124298L;

    @Inject
    private NewsService service;

}
