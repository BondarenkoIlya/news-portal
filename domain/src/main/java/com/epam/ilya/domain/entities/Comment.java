package com.epam.ilya.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Comment")
public class Comment extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5032854930770123363L;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "\"date\"")
    private LocalDate date;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "NEWS_ID", insertable = false, updatable = false, nullable = false)
    private News news;

    @Column(name = "NEWS_ID")
    private int newsId;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + getId() + '\'' +
                "author='" + author + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", news=" + news +
                '}';
    }
}
