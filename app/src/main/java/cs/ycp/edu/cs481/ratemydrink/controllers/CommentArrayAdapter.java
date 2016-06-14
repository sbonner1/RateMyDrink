package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import Model.Comment;

/**
 * An adapter to convert a Comment[] into a JSON object.
 */
public class CommentArrayAdapter extends TypeAdapter<Comment[]>{

    @Override
    public void write(JsonWriter writer, Comment[] value) throws IOException {
        writer.nullValue();
    }

    @Override
    public Comment[] read(JsonReader reader) throws IOException {
        if(reader.peek() == JsonToken.NULL){
            reader.nextNull();
            return null;
        }

        ArrayList<Comment> commentsList = new ArrayList<Comment>();

        while(reader.hasNext()){
            Comment comment = new Comment();
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
            commentsList.add(comment);
        }

        return (Comment[]) commentsList.toArray();
    }
}
