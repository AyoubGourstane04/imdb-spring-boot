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
    private String Title;
    private String Director;
    private List<String> MainCast;
    private Integer CommentsCount;
    private Integer LikesCount;
    private List<Comment> Comments;

    public Film(){}

    public Film(String Title, String Director, List<String> MainCast, Integer CommentsCount, Integer LikesCount, List<Comment> Comments){
        this.Title = Title;
        this.Director = Director;
        this.MainCast = MainCast;
        this.CommentsCount = CommentsCount;
        this.LikesCount = LikesCount;
        this.Comments = Comments;
    }

    public ObjectId getId() {
        return this.id;
    }

    public String getTitle(){
        return this.Title;
    }

    public void setTitle(String Title){
        this.Title = Title;
    }

    public String getDirector(){
        return this.Director;
    }

    public void setDirector(String Director){
        this.Director = Director;
    }

    public List<String> getMainCast(){
        return this.MainCast;
    }

    public void setMainCast(List<String> MainCast){
        this.MainCast = MainCast;
    }

    public Integer getCommentsCount(){
        return this.CommentsCount;
    }

    public void setCommentsCount(Integer CommentsCount){
        this.CommentsCount = CommentsCount;
    }

    public Integer getLikesCount(){
        return this.LikesCount;
    }

    public void setLikesCount(Integer LikesCount){
        this.LikesCount = LikesCount;
    }

    public List<Comment> getComments() {
        return this.Comments;
    }

    public void setComments(List<Comment> Comments) {
        this.Comments = Comments;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;

        Film film = (Film)obj;

        return Objects.equals(id, film.id)
                && Objects.equals(Title, film.Title)
                && Objects.equals(Director, film.Director)
                && Objects.equals(MainCast, film.MainCast)
                && Objects.equals(CommentsCount, film.CommentsCount)
                && Objects.equals(LikesCount, film.LikesCount)
                && Objects.equals(Comments, film.Comments);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, Title, Director, MainCast, CommentsCount, LikesCount, Comments);
    }

}
