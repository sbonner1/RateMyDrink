package cs.ycp.edu.cs481.ratemydrink.fragements;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import Model.Liquor;
import Model.LiquorType;
import Model.Drink;
import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.PostNewLiquorAsync;
//import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.PostNewLiquorAsync;

/**
 * Created by Aaron on 3/26/2015.
 */
public class AddLiquorFragment extends Fragment{
    String tag = "Null Widget";

    private EditText liquorName, liquorDesc, liquorABV;
    private Spinner spinner;
    private Button submit;

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
        View rootView = inflater.inflate(R.layout.fragment_add_liquor, container, false);

        liquorName = (EditText) rootView.findViewById(R.id.new_liquor_field);
        liquorDesc = (EditText) rootView.findViewById(R.id.add_liq_desc_field);
        liquorABV = (EditText) rootView.findViewById(R.id.add_liq_abv_field);

        spinner = (Spinner) rootView.findViewById((R.id.new_liquor_spinner));
        spinner.setAdapter(new ArrayAdapter<LiquorType>(getActivity(), android.R.layout.simple_spinner_item, LiquorType.values()));

        submit = (Button) rootView.findViewById(R.id.add_liquor);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check to make sure all fields are filled out so no null values are sent to database
                if(isEmpty(liquorName) == true || isEmpty(liquorDesc) == true || isEmpty(liquorABV) == true) {
                    Toast.makeText(getActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Liquor newLiquor = createLiquor();
                    PostNewLiquorAsync postLiquor = new PostNewLiquorAsync();
                    postLiquor.execute(newLiquor);
                }
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
     * creates a new Liquor object from the information entered in the AddBeerFragment
     *
     * @return a new Liquor object containing then entered information
     */
    private Liquor createLiquor(){
        String name = liquorName.getText().toString();
        String desc = liquorDesc.getText().toString();
        String abv = liquorABV.getText().toString();
        LiquorType type = (LiquorType) spinner.getSelectedItem();
        Toast.makeText(getActivity(), "adding to database", Toast.LENGTH_SHORT).show();
        Liquor newLiquor = new Liquor(name, desc, Double.valueOf(abv), null);
        newLiquor.setLiquorTypeWithString(type.toString().toUpperCase());
        return newLiquor;
    }

    private Drink createDrink(){
        String name = liquorName.getText().toString();
        String desc = liquorDesc.getText().toString();
        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
        return new Drink(name, desc);
    }
    //Simple method to check if an edit text field is empty
    private boolean isEmpty(EditText Text) {
        return Text.getText().toString().trim().length() == 0;
    }
}
