package cs.ycp.edu.cs481.ratemydrink.fragements;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rateMyDrink.modelClasses.MixedDrink;

import java.util.concurrent.ExecutionException;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.GetMixedDrinkAsync;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class MixedDrinkFragment extends Fragment {

    MixedDrink mMixedDrink;
    
    public MixedDrinkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = -1;

        if (getArguments().containsKey(BeerDetailFragment.ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            id = Integer.valueOf(getArguments().getString(BeerDetailFragment.ARG_ITEM_ID));
        }

        if(id > 0){
            GetMixedDrinkAsync getBeer = new GetMixedDrinkAsync();
            getBeer.execute(id);
            try {
                try {
                    mMixedDrink = getBeer.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mixed_drink, container, false);

        if(mMixedDrink != null) {
            ((TextView) v.findViewById(R.id.beer_name)).setText(mMixedDrink.getDrinkName());
            ((TextView) v.findViewById(R.id.beer_desc)).setText(mMixedDrink.getDescription());
            //((TextView) v.findViewById(R.id.beer_type)).setText(mMixedDrink.getMaxIngredientReadableType());
            ((TextView) v.findViewById(R.id.beerAvgRate)).setText(mMixedDrink.getRating() + "");
        }

        return v;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
