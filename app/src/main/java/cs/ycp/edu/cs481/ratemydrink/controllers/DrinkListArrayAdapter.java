package cs.ycp.edu.cs481.ratemydrink.controllers;

/**
 * Created by Aaron on 2/24/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rateMyDrink.modelClasses.Drink;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.dummy.DummyBeers;

/**
 * An extended adapter so that the drink list can be populated with custom item views
 */
public class DrinkListArrayAdapter<T> extends ArrayAdapter<T> {

    private LayoutInflater inflater = null;

    //another constructor
    public DrinkListArrayAdapter(Context context, int resource, int textViewResourceId, T[] drinkArray){
        super(context, resource, textViewResourceId, drinkArray);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //overwritten implementation of getView() to populate list with image, name, and description of a drink
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_layout, null);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        TextView textView1 = (TextView)convertView.findViewById(R.id.textView1);
        TextView textView2 = (TextView)convertView.findViewById(R.id.textView2);
        TextView textView3 = (TextView)convertView.findViewById(R.id.beer_type);
        TextView textView4 = (TextView)convertView.findViewById(R.id.beer_abv);
        TextView textView5 = (TextView)convertView.findViewById(R.id.beer_cal);
        TextView textView6 = (TextView)convertView.findViewById(R.id.beerAvgRate);

        Drink drink = (Drink)getItem(position);

        textView1.setText(drink.getDrinkName());
        //textView2.setText(drink.getDescription());
//        textView3.setText(drink.type);
//        textView4.setText(drink.ABV);
//        textView5.setText(Float.toString(drink.calories));
//        textView6.setText(Float.toString(drink.rating));

        //imageView.setPadding(8, 8, 8, 8);
        //imageView.setImageResource(R.drawable.ic_launcher);

        return convertView;
    }

}
