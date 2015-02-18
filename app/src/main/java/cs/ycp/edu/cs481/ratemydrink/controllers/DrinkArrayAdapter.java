package cs.ycp.edu.cs481.ratemydrink.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.dummy.DummyContent;

/**
 * An extended adapter so that the drink list can be populated with custom item views
 */
public class DrinkArrayAdapter<T> extends ArrayAdapter<T> {

    private LayoutInflater inflater = null;

    //constructor
    public DrinkArrayAdapter(Context context, int resource, T[] drinkArray) {
        super(context, resource, drinkArray);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //another constructor
    public DrinkArrayAdapter(Context context, int resource, int textViewResourceId, T[] drinkArray){
        super(context, resource, textViewResourceId, drinkArray);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //overwritten implementation of getView() to populate list with image, name, and description of a drink
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_layout, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        TextView textView1 = (TextView)convertView.findViewById(R.id.textView1);
        TextView textView2 = (TextView)convertView.findViewById(R.id.textView2);

        T drink = (T)getItem(position);

        textView1.setText(drink.name);
        textView2.setText(drink.description);
        imageView.setPadding(8, 8, 8, 8);
        imageView.setImageResource(R.drawable.ic_launcher);

        return convertView;
    }

}
