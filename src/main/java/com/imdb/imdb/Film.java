package com.imdb.imdb;

import java.util.List;
// import java.util.Objects;

// import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "films")
public class Film {
    @Id
    private String id;
    private String title;
    private String director;
    private List<String> mainCast;
    private Integer commentsCount;
    private Integer likesCount;
    private List<Comment> comments;
    // @JsonIgnore
    private Image image;

}
