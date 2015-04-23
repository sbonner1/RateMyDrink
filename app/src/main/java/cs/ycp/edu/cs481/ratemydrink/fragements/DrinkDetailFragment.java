package cs.ycp.edu.cs481.ratemydrink.fragements;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.rateMyDrink.modelClasses.Beer;
import com.rateMyDrink.modelClasses.Comment;
import com.rateMyDrink.modelClasses.Drink;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.controllers.DrinkListArrayAdapter;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.GetBeerAsync;
import cs.ycp.edu.cs481.ratemydrink.controllers.web_controllers.GetCommentsAsync;

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
    private Beer mBeer;                 //beer object to be posted to the database
    private RatingBar ratingBar;        //the rating bar
    private TextView txtRatingValue;
    private EditText commentEditText;
    private Button addComment;
    private ArrayList<String> comments;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DrinkDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = -1;

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            id = Integer.valueOf(getArguments().getString(ARG_ITEM_ID));
        }

        if(id > 0){
            GetBeerAsync getBeer = new GetBeerAsync();
            getBeer.execute(id);
            try {
                try {
                    mBeer = getBeer.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            //error
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_beer_drink_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mBeer != null) {
            ((TextView) rootView.findViewById(R.id.beer_name)).setText(mBeer.getDrinkName());
            ((TextView) rootView.findViewById(R.id.beer_desc)).setText(mBeer.getDescription());
            ((TextView) rootView.findViewById(R.id.beer_abv)).setText("ABV: " + mBeer.getABV());
            ((TextView) rootView.findViewById(R.id.beer_cal)).setText("Cal: " + mBeer.getCalories());
            ((TextView) rootView.findViewById(R.id.beer_type)).setText(mBeer.getBeerTypeReadableName());
            ((TextView) rootView.findViewById(R.id.beerAvgRate)).setText(mBeer.getRating() + "");
            //Pre-fills the star with avg user rating
            ((RatingBar) rootView.findViewById(R.id.BeerRatingBar)).setRating(mBeer.getRating());


            final ListView commentsList = (ListView) rootView.findViewById(R.id.comments_listview);

            comments = new ArrayList<String>();

            GetCommentsAsync getComments = new GetCommentsAsync();
            getComments.execute(0, 0);

            try {
                try{
                    Comment[] commentArr = getComments.get();
                    if(commentArr != null){
                        for(Comment comment : commentArr){
                            comments.add(comment.getComment());
                        }
                    }
                }catch(ExecutionException e){
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final ArrayAdapter<String> commentAdapter = new ArrayAdapter<String>(
                    getActivity().getBaseContext(), android.R.layout.simple_expandable_list_item_1, comments);

            commentsList.setAdapter(commentAdapter);

            commentEditText = (EditText) rootView.findViewById(R.id.comments);

            addComment = (Button) rootView.findViewById(R.id.button);
            addComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!commentEditText.getText().toString().equals("")){
                        comments.add(commentEditText.getText().toString());
                        commentAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        return rootView;
    }
}
