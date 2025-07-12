package com.imdb.imdb;


import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private String content;
    private LocalDateTime dateTime;

    public Comment() {}

    public Comment(String content, LocalDateTime dateTime) {
        this.content = content;
        this.dateTime=dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void SetDate(LocalDateTime dateTime) {
        this.dateTime=dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(content, comment.content) &&
               Objects.equals(dateTime, comment.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, dateTime);
    }
}
