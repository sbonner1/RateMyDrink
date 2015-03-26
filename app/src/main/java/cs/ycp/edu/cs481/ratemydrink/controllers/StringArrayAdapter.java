package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 3/23/2015.
 */
public class StringArrayAdapter extends TypeAdapter<String[]> {

    @Override
    public void write(JsonWriter writer, String[] s) throws IOException {

    }

    @Override
    public String[] read(JsonReader reader) throws IOException {
        if(reader.peek() == JsonToken.NULL){
            reader.nextNull();
            return null;
        }

        List<String> nameList = new LinkedList<>();

        //reader.beginArray();
            while(reader.hasNext()){
                reader.beginObject();
                    nameList.add(reader.nextString());
                reader.endObject();
            }
        //reader.endArray();

        return (String[]) nameList.toArray();
    }
}
