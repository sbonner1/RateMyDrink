package cs.ycp.edu.cs482.modelclasses;

import java.util.ArrayList;

/**
 * Created by shanembonner on 12/28/14.
 */
public class MixedDrink extends Drink {
    private ArrayList<String> ingredients;

    public MixedDrink(){
        ingredients = new ArrayList<String>();
    }

    protected void setIngredients(ArrayList<String> ingrList){
        for(int i = 0; i < ingrList.size(); i++){
            this.ingredients.set(i, ingrList.get(i));
        }
    }
}
