package ie.itcarlow.sra;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class DrawerActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mNavigationTitles;
	
	SessionManager session ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_drawer);
		// Set the draw layout
		DrawerLayout();
		// Highlights the current activity in the drawer
		List<String> navigationList = Arrays.asList(mNavigationTitles);
		int index = navigationList.indexOf(mDrawerTitle);
		mDrawerList.setItemChecked(index, true);
		
		session = new SessionManager(getApplicationContext());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return true;
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	public void selectItem(int position) {

		switch (position) {
		case 0:
			Intent feedIntent = new Intent(this, NewsFeed.class);
			startActivity(feedIntent);
			break;
		case 1:
			Intent searchIntent = new Intent(this, SearchActivity.class);
			startActivity(searchIntent);
			break;
		case 2:
			Intent profileIntent = new Intent(this, Profile.class);
			startActivity(profileIntent);
			break;
		case 3:
			Intent shoppinglistIntent = new Intent(this, ShoppingList.class);
			startActivity(shoppinglistIntent);
			break;
		case 4:
			Intent messagesIntent = new Intent(this, Messages.class);
			startActivity(messagesIntent);
			break;
		case 5:
			Intent requestsIntent = new Intent(this, Requests.class);
			startActivity(requestsIntent);
			break;
		case 6:
			Intent friendSearchIntent = new Intent(this, FriendSearch.class);
			startActivity(friendSearchIntent);
			break;
		case 7:
			session.logoutUser();
			break;
		default:

			break;

		}
		mDrawerLayout.closeDrawer(mDrawerList);
		finish();
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void DrawerLayout() {
		mTitle = mDrawerTitle = getTitle();
		mNavigationTitles = getResources().getStringArray(
				R.array.navigation_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mNavigationTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

}
