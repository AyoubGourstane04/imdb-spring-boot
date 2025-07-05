package com.imdb.imdb;


import java.util.Objects;

public class Comment {
    private String content;
    private String date;

    public Comment() {}

    public Comment(String content, String date) {
        this.content = content;
        this.date=date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void SetDate(String date) {
        this.date=date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(content, comment.content) &&
               Objects.equals(date, comment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, date);
    }
}
