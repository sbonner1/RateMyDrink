package cs.ycp.edu.cs481.ratemydrink.fragements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.dummy.DummyBeers;
import cs.ycp.edu.cs481.ratemydrink.dummy.DummyContent;

/**
 * A fragment representing a single Drink detail screen.
 * This fragment is either contained in a {@link cs.ycp.edu.cs481.ratemydrink.activities.DrinkListActivity}
 * in two-pane mode (on tablets) or a {@link cs.ycp.edu.cs481.ratemydrink.activities.DrinkDetailActivity}
 * on handsets.
 */
public class DrinkDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";


    /**
     * The dummy content this fragment is presenting.
     */
    private DummyBeers.DummyBeer mItem;
    private RatingBar ratingBar;
    private TextView txtRatingValue;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DrinkDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyBeers.BEER_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }

        //Next, populate list based on what type of drink the user selected to look up
       // if(mItem.name == "Beer"){

        //}

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beer_drink_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.beer_name)).setText(mItem.name);
            ((TextView) rootView.findViewById(R.id.beer_desc)).setText(mItem.description);
            ((TextView) rootView.findViewById(R.id.beer_abv)).setText("ABV: " + mItem.ABV);
            ((TextView) rootView.findViewById(R.id.beer_cal)).setText("Cal: " + mItem.calories);
            ((TextView) rootView.findViewById(R.id.beer_type)).setText(mItem.type);
            ((TextView) rootView.findViewById(R.id.beerAvgRate)).setText(mItem.rating + "");
            //Pre-fills the star with avg user rating
            ((RatingBar) rootView.findViewById(R.id.BeerRatingBar)).setRating(mItem.rating);
        }

        return rootView;
    }
}
