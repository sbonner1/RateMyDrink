package cs.ycp.edu.cs481.ratemydrink.fragements;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rateMyDrink.modelClasses.Ingredient;
import com.rateMyDrink.modelClasses.LiquorType;
import com.rateMyDrink.modelClasses.MixedDrink;

import java.util.ArrayList;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.PostNewMixedDrinkAsync;

/**
 * Created by Aaron on 4/9/2015.
 */
public class AddMixedDrinkFragment extends Fragment {

    String tag = "Null Widget";

    //q1-5 = quantity fields 1-5, i1-5 = ingredient 1-5
    private EditText drinkName, drinkDesc, q1, q2, q3, q4, q5, i4, i5;
    private Button submit;
    private Spinner i1, i2, i3;

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_mixed_drink, container, false);

        //Text fields
        drinkName = (EditText) rootView.findViewById(R.id.drinkName);
        drinkDesc = (EditText) rootView.findViewById(R.id.mdDescField);

        //Quantities
        q1 = (EditText) rootView.findViewById(R.id.quantityOne);
        q2 = (EditText) rootView.findViewById(R.id.quantityTwo);
        q3 = (EditText) rootView.findViewById(R.id.quantityThree);
        q4 = (EditText) rootView.findViewById(R.id.quantityFour);
        q5 = (EditText) rootView.findViewById(R.id.quantityFive);

        //Ingredients, this may need tweaked
        //GETTING NULL POINTER EXCEPTION HERE
        i1 = (Spinner) rootView.findViewById((R.id.ingredientSpinnerOne));

        if(i1 == null){
            Log.d("NULL WIDGET", "i1 is null");
        }

        //i1.setAdapter(new ArrayAdapter<LiquorType>(getActivity(), android.R.layout.simple_spinner_item, LiquorType.values()));
        i2 = (Spinner) rootView.findViewById((R.id.ingredientSpinnerTwo));

        if(i2 == null){
            Log.d("NULL WIDGET", "i2 is null");
        }
        //i2.setAdapter(new ArrayAdapter<LiquorType>(getActivity(), android.R.layout.simple_spinner_item, LiquorType.values()));
        i3 = (Spinner) rootView.findViewById((R.id.ingredientSpinnerThree));

        if(i3 == null){
            Log.d("NULL WIDGET", "i3 is null");
        }

        //i3.setAdapter(new ArrayAdapter<LiquorType>(getActivity(), android.R.layout.simple_spinner_item, LiquorType.values()));
        //Two slots for other ingredients
        i4 = (EditText) rootView.findViewById(R.id.otherIngredOne);
        i5 = (EditText) rootView.findViewById(R.id.otherIngredTwo);

        submit = (Button) rootView.findViewById(R.id.mdSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MixedDrink newMixedDrink = createMixedDrink();
                //Do Post here
                Toast.makeText(getActivity(), "Post Mixed Drink", Toast.LENGTH_SHORT).show();

                PostNewMixedDrinkAsync postMixedDrink = new PostNewMixedDrinkAsync();
                postMixedDrink.execute(newMixedDrink);

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            //setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * creates a new Beer object from the information entered in the AddBeerFragment
     *
     * @return a new Beer object containing then entered information
     */
    private MixedDrink createMixedDrink(){
        String name = drinkName.getText().toString();
        String desc = drinkDesc.getText().toString();
        LiquorType type1 = LiquorType.valueOf(((String) i1.getSelectedItem()).toUpperCase());
        LiquorType type2 = LiquorType.valueOf(((String) i2.getSelectedItem()).toUpperCase());
        LiquorType type3 = LiquorType.valueOf(((String) i3.getSelectedItem()).toUpperCase());
       // Toast.makeText(getActivity(), type1.toString(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getActivity(), type2.toString(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(getActivity(), type3.toString(), Toast.LENGTH_SHORT).show();

        ArrayList<Ingredient> ingredients= new ArrayList<Ingredient>();

        //Check if user input fields are empty
        if(isEmpty(i4) == false && isEmpty((q4)) == false) {
            String userIngredientOne = i4.getText().toString();
            String userQuantityOne = q4.toString();
            ingredients.add(new Ingredient(0, userIngredientOne, Double.valueOf(userQuantityOne)));
        }
        //Check if user input fields are empty
        if(isEmpty(i5) == false && isEmpty((q5)) == false) {
            String userIngredientTwo = i5.getText().toString();
            String userQuantityTwo = q5.toString();
            ingredients.add(new Ingredient(0, userIngredientTwo, Double.valueOf(userQuantityTwo)));
        }

        ingredients.add(new Ingredient(0, "ingredient1", Double.valueOf("5")));
        ingredients.add(new Ingredient(0, "ingredient2", Double.valueOf("3")));

        //MixedDrink needs to accept array lists
        MixedDrink mixedDrink = new MixedDrink(name, desc, "", type1, ingredients);

        return mixedDrink;
    }

    //Simple method to check if an edit text field is empty
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}
