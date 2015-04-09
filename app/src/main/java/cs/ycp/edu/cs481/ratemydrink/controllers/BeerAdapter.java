package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.rateMyDrink.modelClasses.Beer;

import java.io.IOException;

/**
 * Created by user on 3/19/2015.
 */
public class BeerAdapter extends TypeAdapter<Beer> {
    @Override
    public void write(JsonWriter jsonWriter, Beer beer) throws IOException {
        if(beer == null){
            jsonWriter.nullValue();
            return;
        }

        jsonWriter.beginObject();
        jsonWriter.name("drinkName").value(beer.getDrinkName());
        jsonWriter.name("description").value(beer.getDescription());
        jsonWriter.name("rating").value(beer.getRating());
        jsonWriter.name("id").value(String.valueOf(beer.getId()));
        jsonWriter.name("abv").value(beer.getABV());
        jsonWriter.name("calories").value(String.valueOf(beer.getCalories()));
        jsonWriter.name("beerType").value("LAGER");
        jsonWriter.endObject();

    }

    @Override
    public Beer read(JsonReader jsonReader) throws IOException {
        if(jsonReader.peek() == JsonToken.NULL){
            jsonReader.nextNull();
            return null;
        }

        Beer beer = new Beer();

        jsonReader.beginObject();
            while(jsonReader.hasNext()){
                String field = jsonReader.nextName();
                if(field.equals("drinkName")){
                    beer.setDrinkName(jsonReader.nextString());
                }
                if(field.equals("description")){
                    beer.setDescription(jsonReader.nextString());
                }
                if(field.equals("rating")){
                    beer.setRating(Float.valueOf(jsonReader.nextString()));
                }
                if(field.equals("id")){
                    beer.setId(Integer.valueOf(jsonReader.nextString()));
                }
                if(field.equals("abv")){
                    beer.setABV(Integer.valueOf(jsonReader.nextString()));
                }
                if(field.equals("calories")){
                    beer.setCalories(Integer.valueOf(jsonReader.nextString()));
                }else{
                    jsonReader.skipValue();
                }
            }
        jsonReader.endObject();

        return beer;
    }
}
