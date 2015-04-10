package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.rateMyDrink.modelClasses.Liquor;

import java.io.IOException;

/**
 * An adapter to convert JSON objects into Liquor objects as well as to convert Liquor objects to JSON objects
 */
public class LiquorAdapter extends TypeAdapter<Liquor> {
    @Override
    public void write(JsonWriter jsonWriter, Liquor liquor) throws IOException {
        if(liquor == null){
            jsonWriter.nullValue();
            return;
        }

        jsonWriter.beginObject();
        jsonWriter.name("drinkName").value(liquor.getDrinkName());
        jsonWriter.name("description").value(liquor.getDescription());
        jsonWriter.name("rating").value(liquor.getRating());
        jsonWriter.name("id").value(String.valueOf(liquor.getId()));
        jsonWriter.name("alcoholContent").value(liquor.getAlcoholContent());
        jsonWriter.name("liquorType").value(liquor.getLiquorTypeReadableType());
        jsonWriter.endObject();
    }

    @Override
    public Liquor read(JsonReader jsonReader) throws IOException {
        if(jsonReader.peek() == JsonToken.NULL){
            jsonReader.nextNull();
            return null;
        }

        Liquor liquor = new Liquor();

        jsonReader.beginObject();
            while(jsonReader.hasNext()){

                String field = "";
                if(jsonReader.peek() == JsonToken.NAME){
                    field = jsonReader.nextName();
                }

                if(field.equals("drinkName")){
                    liquor.setDrinkName(jsonReader.nextString());
                }
                if(field.equals("description")){
                    liquor.setDescription(jsonReader.nextString());
                }
                if(field.equals("rating")){
                    liquor.setRating(Float.valueOf(jsonReader.nextString()));
                }
                if(field.equals("id")){
                    liquor.setId(Integer.valueOf(jsonReader.nextString()));
                }
                if(field.equals("alcoholContent")){
                    liquor.setAlcoholContent(Float.valueOf(jsonReader.nextString()));
                }
                if(field.equals("liquorType")) {
                    liquor.setLiquorTypeWithString(jsonReader.nextString());
                }else{
                    jsonReader.skipValue();
                }
            }
        jsonReader.endObject();

        return liquor;
    }

}
