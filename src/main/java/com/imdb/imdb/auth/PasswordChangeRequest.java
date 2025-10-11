package com.imdb.imdb.auth;



import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "passwordChangeRequests")
public class PasswordChangeRequest {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private String password;
    private String confirmPassword;
}
