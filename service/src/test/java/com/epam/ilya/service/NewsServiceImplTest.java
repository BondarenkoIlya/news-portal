package com.epam.ilya.service;

import com.epam.ilya.api.NewsService;
import com.epam.ilya.dao.impl.NewsDaoJPA;
import com.epam.ilya.domain.entities.News;
import com.epam.ilya.impl.NewsServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class NewsServiceImplTest {

    private static NewsDaoJPA daoJPA;

    @Before
    public void init() {
        daoJPA = mock(NewsDaoJPA.class);
        News news = new News();
        news.setTitle("test");
        when(daoJPA.findById(anyLong())).thenReturn(news);
    }

    @Test
    public void shouldReturnNewsById() {
        NewsService service = new NewsServiceImpl(daoJPA, null);
        News news = service.findById(anyLong());
        assertThat(news.getTitle(), equalTo("test"));
    }

}
