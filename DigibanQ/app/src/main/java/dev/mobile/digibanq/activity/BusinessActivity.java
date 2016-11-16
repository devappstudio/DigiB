package dev.mobile.digibanq.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

import info.androidhive.digibanq.R;

/**
 * Created by banktech on 7/13/2016.
 */
public class BusinessActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab1,fab2,fab3,fab4,fab5,fab6;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    final Context context = this;
    private ImageButton button;

    private Toolbar toolbar;
    private ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

    private static final String PREFERENCES_FILE = "mymaterialapp_settings";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private int mCurrentSelectedPosition;
    TextView feeds,wishlist,delivery, name, companyName,recent_activities,promo_coupons,goal_coupons,notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_business);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab4 = (FloatingActionButton)findViewById(R.id.fab4);
        fab5 = (FloatingActionButton)findViewById(R.id.fab5);
        fab6 = (FloatingActionButton)findViewById(R.id.fab6);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fab5.setOnClickListener(this);
        fab6.setOnClickListener(this);*/



        //viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);



        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this); */

        button = (ImageButton)findViewById(R.id.get_now);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.buy_options_custom);
               /* dialog.setTitle("Select Payment Method");

                // set the custom dialog components - text, image and button
               TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.logo);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });*/

                dialog.show();

            }
        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        name=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.navigation_item_1));
        companyName=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.navigation_item_2));
        feeds=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.navigation_item_4));
        wishlist=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.notes));
        delivery=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.navigation_item_7));
        recent_activities=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.recent_activities));
        promo_coupons=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.promo_coupons));
        goal_coupons=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.goal_specific_coupons));
        notes=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.notes));
        initializeCountDrawer();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);

        mUserLearnedDrawer = Boolean.valueOf(readSharedSetting(this, PREF_USER_LEARNED_DRAWER, "false"));

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        setUpNavDrawer();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                mDrawerLayout.closeDrawers();
                switch (id){
                    case R.id.recent_activities:
                        Intent recent_act = new Intent(BusinessActivity.this, Recent_Activities.class);
                        startActivity(recent_act);
                        break;
                    case R.id.promo_coupons:
                        Intent promo_coups = new Intent(BusinessActivity.this, Recent_Activities.class);
                        startActivity(promo_coups);
                        break;
                    case R.id.goal_specific_coupons:
                        Intent goal_specific_coups = new Intent(BusinessActivity.this, GoalSpecific.class);
                        startActivity(goal_specific_coups);
                        break;
                    case R.id.notes:
                        Intent wishlist = new Intent(BusinessActivity.this, WishList.class);
                        startActivity(wishlist);
                        break;
                }
                return false;
            }
        });
        //NavigationView = (NavigationView) findViewById(R.id.nav_view);
        // mContentFrame = (FrameLayout) findViewById(R.id.nav_contentframe);

    }

    public void recent_act(){
        // Perform action on click
        Intent recent_act = new Intent(BusinessActivity.this, BusinessActivity.class);
        BusinessActivity.this.startActivity(recent_act);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        Menu menu = mNavigationView.getMenu();
        menu.getItem(mCurrentSelectedPosition).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_money:
                Intent money = new Intent(BusinessActivity.this, FinancialActivity.class);
                BusinessActivity.this.startActivity(money);
                return true;
            case R.id.action_store:
                Intent store = new Intent(BusinessActivity.this, StoreActivity.class);
                BusinessActivity.this.startActivity(store);
                return true;
            case R.id.action_inbox:
                Intent msgs = new Intent(BusinessActivity.this, MessagesActivity.class);
                BusinessActivity.this.startActivity(msgs);
                return true;
            case R.id.action_search:
                Intent srch = new Intent(BusinessActivity.this, SearchActivity.class);
                BusinessActivity.this.startActivity(srch);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }


    private void setUpNavDrawer() {
        if (toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(R.drawable.ic_drawer);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        if (!mUserLearnedDrawer) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            mUserLearnedDrawer = true;
            saveSharedSetting(this, PREF_USER_LEARNED_DRAWER, "true");
        }

    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }
    private void initializeCountDrawer(){
        //Gravity property aligns the text
        name.setGravity(Gravity.CENTER_VERTICAL);
        name.setTypeface(null, Typeface.BOLD);
        name.setTextColor(getResources().getColor(R.color.timestamp));
        name.setText("92");
        companyName.setGravity(Gravity.CENTER_VERTICAL);
        companyName.setTypeface(null, Typeface.BOLD);
        companyName.setTextColor(getResources().getColor(R.color.timestamp));
        companyName.setText("102");
        feeds.setGravity(Gravity.CENTER_VERTICAL);
        feeds.setTypeface(null, Typeface.BOLD);
        feeds.setTextColor(getResources().getColor(R.color.colorAccent));
        feeds.setText("23");
        wishlist.setGravity(Gravity.CENTER_VERTICAL);
        wishlist.setTypeface(null, Typeface.BOLD);
        wishlist.setTextColor(getResources().getColor(R.color.colorAccent));
        wishlist.setText("44");
        delivery.setGravity(Gravity.CENTER_VERTICAL);
        delivery.setTypeface(null, Typeface.BOLD);
        delivery.setTextColor(getResources().getColor(R.color.colorAccent));
        delivery.setText("1hr 2mins");
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        return false;
    }
}
