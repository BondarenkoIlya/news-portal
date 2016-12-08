package com.epam.ilya.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5032854930770123363L;

    @Column(name = "author")
    private String author;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "news_id", insertable = false, updatable = false, nullable = false)
    private News news;

    @Column(name = "news_id")
    private Long newsId;

    public Comment() {
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + getId() + '\'' +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", news=" + news +
                '}';
    }
}
