package cs.ycp.edu.cs481.ratemydrink.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.fragements.DrinkDetailFragment;
import cs.ycp.edu.cs481.ratemydrink.fragements.DrinkListFragment;


/**
 * An activity representing a list of Drinks. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link cs.ycp.edu.cs481.ratemydrink.activities.DrinkDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link cs.ycp.edu.cs481.ratemydrink.fragements.DrinkListFragment} and the item details
 * (if present) is a {@link cs.ycp.edu.cs481.ratemydrink.fragements.DrinkDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link cs.ycp.edu.cs481.ratemydrink.fragements.DrinkListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class DrinkListActivity extends ActionBarActivity implements DrinkListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (findViewById(R.id.drink_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((DrinkListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.drink_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link DrinkListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DrinkDetailFragment.ARG_ITEM_ID, id);
            DrinkDetailFragment fragment = new DrinkDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.drink_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, DrinkDetailActivity.class);
            detailIntent.putExtra(DrinkDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //This allows user to go back to drink type activity
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, TypeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
