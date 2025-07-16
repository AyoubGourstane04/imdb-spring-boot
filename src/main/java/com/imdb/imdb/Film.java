package com.imdb.imdb;

import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "films")
public class Film {
    @Id
    private ObjectId id;
    private String title;
    private String director;
    private List<String> mainCast;
    private Integer commentsCount;
    private Integer likesCount;
    private List<Comment> comments;
    
    public Film(){}

    public Film(String title, String director, List<String> mainCast, Integer commentsCount, Integer likesCount, List<Comment> comments){
        this.title = title;
        this.director = director;
        this.mainCast = mainCast;
        this.commentsCount = commentsCount;
        this.likesCount = likesCount;
        this.comments = comments;
    }


    public ObjectId getId() {
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDirector(){
        return this.director;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public List<String> getMainCast(){
        return this.mainCast;
    }

    public void setMainCast(List<String> mainCast){
        this.mainCast = mainCast;
    }

    public Integer getCommentsCount(){
        return this.commentsCount;
    }

    public void setCommentsCount(Integer commentsCount){
        this.commentsCount = commentsCount;
    }

    public Integer getLikesCount(){
        return this.likesCount;
    }

    public void setLikesCount(Integer likesCount){
        this.likesCount = likesCount;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;

        Film film = (Film)obj;

        return Objects.equals(id, film.id)
                && Objects.equals(title, film.title)
                && Objects.equals(director, film.director)
                && Objects.equals(mainCast, film.mainCast)
                && Objects.equals(commentsCount, film.commentsCount)
                && Objects.equals(likesCount, film.likesCount)
                && Objects.equals(comments, film.comments);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, title, director, mainCast, commentsCount, likesCount, comments);
    }


}
