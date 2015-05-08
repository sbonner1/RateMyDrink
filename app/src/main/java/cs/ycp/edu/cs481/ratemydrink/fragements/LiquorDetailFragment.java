package cs.ycp.edu.cs481.ratemydrink.fragements;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rateMyDrink.modelClasses.Liquor;

import java.util.concurrent.ExecutionException;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.getLiquorAsync;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class LiquorDetailFragment extends Fragment {

    private Liquor mLiquor;

    public LiquorDetailFragment() {
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
            getLiquorAsync getBeer = new getLiquorAsync();
            getBeer.execute(id);
            try {
                try {
                    mLiquor = getBeer.get();
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
        View v = inflater.inflate(R.layout.fragment_liquor_detail, container, false);

        if(mLiquor == null){
            Log.d("NULL", "mLiquor is null");
        }

        if(mLiquor != null) {
            ((TextView) v.findViewById(R.id.liquor_name)).setText(mLiquor.getDrinkName());
            ((TextView) v.findViewById(R.id.beer_desc)).setText(mLiquor.getDescription());
            ((TextView) v.findViewById(R.id.beer_abv)).setText("ABV: " + mLiquor.getAlcoholContent());
            ((TextView) v.findViewById(R.id.liquor_type)).setText(mLiquor.getLiquorTypeReadableType());
            ((TextView) v.findViewById(R.id.beerAvgRate)).setText(mLiquor.getRating() + "");
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
