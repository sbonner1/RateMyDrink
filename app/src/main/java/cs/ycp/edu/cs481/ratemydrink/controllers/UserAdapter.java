package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import Model.User;

import java.io.IOException;

/**
 * An adapter for serializing/deserializing User objetcts to JSON and vice-versa.
 */
public class UserAdapter extends TypeAdapter<User> {
    @Override
    public void write(JsonWriter writer, User user) throws IOException {
        if(user == null){
            writer.nullValue();
            return;
        }

        writer.beginObject();
            writer.name("userName").value(user.getUserName());
            writer.name("userPassword").value(user.getUserPassword());
            writer.name("id").value(user.getId());
        writer.endObject();
    }

    @Override
    public User read(JsonReader reader) throws IOException {
       if(reader.peek() == JsonToken.NULL){
           return null;
       }

       User user = new User();

       reader.beginObject();
          while(reader.hasNext()){
            String field = "";
            if(reader.peek() == JsonToken.NAME){
                field = reader.nextName();
            }

            if(field.equals("userName")){
                user.setUserName(reader.nextString());
            }
            if(field.equals("userPassword")){
                user.setUserPassword(reader.nextString());
            }
            if(field.equals("id")){
                user.setId(reader.nextInt());
            }
          }
       reader.endObject();

       return user;
    }
}
