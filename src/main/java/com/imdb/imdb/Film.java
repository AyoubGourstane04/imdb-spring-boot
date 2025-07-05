package com.imdb.imdb;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "films")
public class Film {
    @Id
    private Integer id;
    private String Title;
    private String Director;
    private List<String> MainCast;
    private Integer CommentsCount;
    private Integer LikesCount;

    public Film(){}

    public Film(Integer id, String Title, String Director, List<String> MainCast,Integer CommentsCount, Integer LikesCount){
        this.id=id;
        this.Title=Title;
        this.Director=Director;
        this.MainCast=MainCast;
        this.CommentsCount=CommentsCount;
        this.LikesCount=LikesCount;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getTitle(){
        return this.Title;
    }

    public void setTitle(String Title){
        this.Title=Title;
    }

    public String getDirector(){
        return this.Director;
    }

    public void setDirector(String Director){
        this.Director=Director;
    }

    public List<String> getMainCast(){
        return this.MainCast;
    }

    public void setMainCast(List<String> MainCast){
        this.MainCast=MainCast;
    }

    public Integer getCommentsCount(){
        return this.CommentsCount;
    }

    public void setCommentsCount(Integer CommentsCount){
        this.CommentsCount=CommentsCount;
    }

    public Integer getLikesCount(){
        return this.LikesCount;
    }

    public void setLikesCount(Integer LikesCount){
        this.LikesCount=LikesCount;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()) return false;

        Film film=(Film)obj;

        return Objects.equals(id,film.id) && Objects.equals(Title,film.Title) && Objects.equals(Director,film.Director) 
                && Objects.equals(MainCast,film.MainCast) && Objects.equals(CommentsCount,film.CommentsCount) 
                && Objects.equals(LikesCount,film.LikesCount);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id,Title,Director,MainCast,CommentsCount,LikesCount);
    }

}
