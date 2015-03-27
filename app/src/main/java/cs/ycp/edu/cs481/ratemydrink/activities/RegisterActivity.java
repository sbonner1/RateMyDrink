package cs.ycp.edu.cs481.ratemydrink.activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.fragements.RegisterFragment;

public class RegisterActivity extends ActionBarActivity {

    private String[] navTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private boolean mTwoPane;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.register_container, new RegisterFragment())
                    .commit();
        }

        navTitles = getResources().getStringArray(R.array.navItems);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, navTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


//        mDrawerToggle = new ActionBarDrawerToggle(
//                this,                  /* host Activity */
//                mDrawerLayout,         /* DrawerLayout object */
//                R.drawable.drinkicon,  /* nav drawer icon to replace 'Up' caret */
//                R.string.drawer_open,  /* "open drawer" description */
//                R.string.drawer_close  /* "close drawer" description */
//        ) {
//
//            /** Called when a drawer has settled in a completely closed state. */
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                getActionBar().setTitle(mTitle);
//            }
//
//            /** Called when a drawer has settled in a completely open state. */
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                getActionBar().setTitle(mDrawerTitle);
//            }
//        };

        // Set the drawer toggle as the DrawerListener
        mDrawerList.setItemChecked(0, true);
        setTitle(navTitles[0]);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //This allows user to go back to drink list page
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Allows items from drawer to be selected
    private boolean selectItem(int position) {

        //Open up login page
        if(position == 0)
        {
            Toast.makeText(this, "You already are on login page", Toast.LENGTH_SHORT).show();
        }
        //Open up new drink page
        if(position == 1) {
            Intent newDrinkIntent = new Intent(this, AddBeerActivity.class);
            startActivity(newDrinkIntent);
            return true;
        }
        //This would go to add a liquor drink
        if(position == 2) {
            Intent newLiquorIntent = new Intent(this, AddLiquorActivity.class);
            startActivity(newLiquorIntent);
            return true;
        }
        //This would go to add a mixed drink
        if(position == 3) {
            Intent newMenuIntent = new Intent(this, TypeActivity.class);
            startActivity(newMenuIntent);
            return true;
        }
        //Main menu screen, but you are already here
        if(position == 4) {
            Toast.makeText(this, "You already are on the main menu", Toast.LENGTH_SHORT).show();
        }
        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(navTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
        return false;
    }

    //Click listener on drawer
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

}
