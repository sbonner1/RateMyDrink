package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.BeerType;

import java.io.IOException;

/**
 * Created by user on 3/26/2015.
 */
public class BeerJsonAdapter extends TypeAdapter<Beer>{
    @Override
    public void write(JsonWriter jsonWriter, Beer beer) throws IOException {

    }

    @Override
    public Beer read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL){
            return null;
        }

        Beer beer = new Beer();

        reader.beginObject();

            while(reader.hasNext()){
                String name = reader.nextName();
                switch (name){
                    case "id":
                        beer.setId(reader.nextInt());
                        break;
                    case "drinkName":
                        beer.setDrinkName(reader.nextString());
                        break;
                    case "description":
                        beer.setDescription(reader.nextString());
                        break;
                    case "rating":
                        beer.setRating(0.0f);
                        break;
                    case "drinkType":
                        beer.setBeerType(BeerType.IMPERIAL);
                        break;
                    case "calories":
                        beer.setCalories(reader.nextInt());
                        break;
                    case "abv":
                        beer.setABV(reader.nextInt());
                        break;
                    default:
                        reader.skipValue();
                }
            }
        reader.endObject();

        return beer;
    }
}
