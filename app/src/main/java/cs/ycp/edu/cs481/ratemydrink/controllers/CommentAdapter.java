package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import Model.Comment;

/**
 * An adapter to convert JSON objects into Comment objects as well as to convert Comment objects to JSON objects
 */
public class CommentAdapter extends TypeAdapter<Comment> {
    @Override
    public void write(JsonWriter writer, Comment comment) throws IOException {
        if(comment == null){
            writer.nullValue();
            return;
        }

        writer.beginObject();
            writer.name("drinkId").value(comment.getDrinkId());
            writer.name("username").value(comment.getUsername());
            writer.name("comment").value(comment.getComment());
        writer.endObject();

    }

    @Override
    public Comment read(JsonReader reader) throws IOException {
        if(reader.peek() == JsonToken.NULL){
            reader.nextNull();
            return null;
        }

        Comment comment = new Comment();

        while(reader.hasNext()){
            String name = "";
            if(reader.peek() == JsonToken.NAME){
                name = reader.nextName();
            }

            if(name.equals("drinkId")){
                comment.setDrinkId(reader.nextInt());
            }
            if(name.equals("username")){
                comment.setUsername(reader.nextString());
            }
            if(name.equals("comment")){
                comment.setComment(reader.nextString());
            }else{
                reader.skipValue();
            }
        }

        return comment;
    }
}
