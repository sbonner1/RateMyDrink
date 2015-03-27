package cs.ycp.edu.cs481.ratemydrink.activities;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.rateMyDrink.modelClasses.BeerType;

import java.util.ArrayList;

import cs.ycp.edu.cs481.ratemydrink.R;
import cs.ycp.edu.cs481.ratemydrink.fragements.AddBeerFragment;

public class AddBeerActivity extends ActionBarActivity {

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
        setContentView(R.layout.activity_add_beer);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AddBeerFragment())
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
        mDrawerList.setItemChecked(1, true);
        setTitle(navTitles[1]);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_beer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Allows items from drawer to be selected
    private boolean selectItem(int position) {

        //Open up login page
        if(position == 0)
        {
            Intent newLoginIntent = new Intent(this, LoginActivity.class);
            startActivity(newLoginIntent);
            return true;
        }
        //Open up new drink page
        if(position == 1) {
            Toast.makeText(this, "You already are on the add beer page", Toast.LENGTH_SHORT).show();
        }
        //This would go to add a liquor drink
        if(position == 2) {
            Intent newLiquorIntent = new Intent(this, AddLiquorActivity.class);
            startActivity(newLiquorIntent);
            return true;
        }
        //This would go to add a mixed drink
        if(position == 3) {
            Toast.makeText(this, "Pretend this goes to mixed drink", Toast.LENGTH_SHORT).show();
            return true;
        }
        //Main menu screen, but you are already here
        if(position == 4) {
            Intent newMenuIntent = new Intent(this, TypeActivity.class);
            startActivity(newMenuIntent);
            return true;
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


