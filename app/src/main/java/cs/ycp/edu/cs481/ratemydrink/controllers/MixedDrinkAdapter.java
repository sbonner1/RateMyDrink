package cs.ycp.edu.cs481.ratemydrink.controllers;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.rateMyDrink.modelClasses.Ingredient;
import com.rateMyDrink.modelClasses.LiquorType;
import com.rateMyDrink.modelClasses.MixedDrink;

import java.io.IOException;
import java.util.ArrayList;

/**
 * An adapter to convert JSON objects into MixedDrink objects as well as to convert MixedDrink objects to JSON objects
 */
public class MixedDrinkAdapter extends TypeAdapter<MixedDrink> {
    @Override
    public void write(JsonWriter writer, MixedDrink mixedDrink) throws IOException {
        if(mixedDrink == null){
            writer.nullValue();
            return;
        }

        writer.beginObject();
            writer.name("drinkName").value(mixedDrink.getDrinkName());
            writer.name("description").value(mixedDrink.getDescription());
            writer.name("rating").value(mixedDrink.getRating());
            writer.name("id").value(String.valueOf(mixedDrink.getId()));
            writer.name("maxIngredient").value(mixedDrink.getMaxIngredientReadableType());
            writer.name("ingredients").beginArray();
               ArrayList<Ingredient> ingredientArrList =  mixedDrink.getIngredients();
               for(Ingredient ingrdient : ingredientArrList){
                   writer.beginObject();
                       writer.name("drinkId").value(String.valueOf(mixedDrink.getId()));
                       writer.name("ingredientName").value(ingrdient.getIngredientName());
                       writer.name("amount").value(ingrdient.getAmount());
                   writer.endObject();
               }
            writer.endArray();
        writer.endObject();
    }

    @Override
    public MixedDrink read(JsonReader reader) throws IOException {
        if(reader.peek() == JsonToken.NULL){
            return null;
        }
        MixedDrink mixedDrink = new MixedDrink();

        reader.beginObject();
            while(reader.hasNext()){
                String field = "";
                if(reader.peek() == JsonToken.NAME){
                    field = reader.nextName();
                }

                if(field.equals("drinkName")){
                    mixedDrink.setDrinkName(reader.nextString());
                }
                if(field.equals("description")){
                    mixedDrink.setDescription(reader.nextString());
                }
                if(field.equals("rating")){
                    mixedDrink.setRating(Float.valueOf(reader.nextString()));
                }
                if(field.equals("id")){
                    mixedDrink.setId(Integer.valueOf(reader.nextString()));
                }
                if(field.equals("maxIngredients")){
                    mixedDrink.setMaxIngredient(LiquorType.GIN);
                }
                if(field.equals("ingredients")){
                    mixedDrink.setIngredients(ingredientsDeserialization(reader));
                }
                
            }
        reader.endObject();

        return mixedDrink;
    }

    /**
     * helper method to deserialize the array of ingredients from the JSON object
     *
     * @param reader the JsonReader
     * @return an ArrayList of the mixedDrink's ingredients, null otherwise
     * @throws IOException
     */
    private ArrayList<Ingredient> ingredientsDeserialization(JsonReader reader) throws IOException{
        if(reader.peek() != JsonToken.BEGIN_ARRAY){
            System.out.println(" did not find BEGIN_ARRAY JSON Token....");
            return null;
        }

        ArrayList<Ingredient> ingrArrList = new ArrayList<Ingredient>();

        while(reader.peek() != JsonToken.END_ARRAY){
            Ingredient ingredient = new Ingredient();

            String field = "";
            if(reader.peek() == JsonToken.NAME){
                field = reader.nextName();
            }

            if(field.equals("drinkId")){
               ingredient.setDrinkId(reader.nextInt());
            }
            if(field.equals("ingredientName")){
                ingredient.setIngredientName(reader.nextString());
            }
            if(field.equals("amount")){
                ingredient.setAmount(reader.nextDouble());
            }
            ingrArrList.add(ingredient);
        }
        return ingrArrList;
    }

}
