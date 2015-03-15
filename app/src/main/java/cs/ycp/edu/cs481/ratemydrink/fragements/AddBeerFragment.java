package cs.ycp.edu.cs481.ratemydrink.fragements;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.BeerType;

import cs.ycp.edu.cs481.ratemydrink.R;

public class AddBeerFragment extends Fragment {

    String tag = "Null Widget";

    private EditText beerName, beerDesc, beerABV;
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
        View rootView = inflater.inflate(R.layout.fragment_add_beer, container, false);

        beerName = (EditText) rootView.findViewById(R.id.new_beer_field);
        beerDesc = (EditText) rootView.findViewById(R.id.add_beer_desc_field);
        beerABV = (EditText) rootView.findViewById(R.id.add_beer_abv_field);

        spinner = (Spinner) rootView.findViewById((R.id.new_beer_spinner));
        spinner.setAdapter(new ArrayAdapter<BeerType>(getActivity(), android.R.layout.simple_spinner_item, BeerType.values()));

        submit = (Button) rootView.findViewById(R.id.add_beer);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Beer newBeer = createBeer();
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
     *
     * @return
     */
    private Beer createBeer(){
        String name = beerName.getText().toString();
        String desc = beerDesc.getText().toString();
        String abv = beerABV.getText().toString();
        BeerType type = (BeerType) spinner.getSelectedItem();
        Toast.makeText(getActivity(), type.toString(), Toast.LENGTH_SHORT).show();
        return new Beer(name, desc, Double.valueOf(abv), 0, type);
    }

}
