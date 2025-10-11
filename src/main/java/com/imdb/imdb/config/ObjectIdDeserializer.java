package com.imdb.imdb.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

    @Override
    public ObjectId deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String txt = parser.getText();
        return txt!=null && !txt.isEmpty() ? new ObjectId(txt) : null;
    }
}
