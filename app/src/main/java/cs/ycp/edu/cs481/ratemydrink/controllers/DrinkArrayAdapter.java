package cs.ycp.edu.cs481.ratemydrink.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.rateMyDrink.modelClasses.Drink;

/**
 * An extended adapter so that the drink list can be populated with custom item views
 */
public class DrinkArrayAdapter extends ArrayAdapter {

    //constructor
    public DrinkArrayAdapter(Context context, int resource, Drink[] drinkArray) {
        super(context, resource, drinkArray);
    }

    //overwritten implementation of getView() to populate list with image, name, and description of a drink
    public View getView(int position, View convertView, ViewGroup parent){
        return null;
    }


}
