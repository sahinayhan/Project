package ie.itcarlow.sra;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Listview extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mPlanetTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);

		DrawerLayout();

		String[] codeLearnChapters = new String[] { "Android", "Intro" };
		ArrayAdapter<String> codeLearnArrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, codeLearnChapters);
		ListView codeLearnLessons = (ListView) findViewById(R.id.listView1);
		codeLearnLessons.setAdapter(codeLearnArrayAdapter);
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

	private void selectItem(int position) {
		// update the main content by replacing fragments
		/*
		 * Fragment fragment = new PlanetFragment(); Bundle args = new Bundle();
		 * args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		 * fragment.setArguments(args);
		 * 
		 * FragmentManager fragmentManager = getFragmentManager();
		 * fragmentManager.beginTransaction() .replace(R.id.content_frame,
		 * fragment).commit();
		 * 
		 * // update selected item and title, then close the drawer
		 * mDrawerList.setItemChecked(position, true);
		 * setTitle(mPlanetTitles[position]);
		 * mDrawerLayout.closeDrawer(mDrawerList);
		 */

		switch (position) {
		case 0:
			Fragment fragment = new NewsfeedFragment();
			Bundle args = new Bundle();
			args.putInt(NewsfeedFragment.ARG_PLANET_NUMBER, position);
			fragment.setArguments(args);

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.content_frame, fragment).commit();

			mDrawerList.setItemChecked(position, true);
			setTitle(mPlanetTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);

			break;
		case 1:

			Intent i = new Intent(Listview.this, SearchActivity.class);
			startActivity(i);

			/*
			 * Fragment Sfragment = new SearchFragment(); Bundle args2 = new
			 * Bundle(); args2.putInt(SearchFragment.ARG_PLANET_NUMBER,
			 * position); Sfragment.setArguments(args2);
			 * 
			 * FragmentManager fragmentManager2 = getFragmentManager();
			 * fragmentManager2.beginTransaction() .replace(R.id.content_frame,
			 * Sfragment).commit();
			 * 
			 * mDrawerList.setItemChecked(position, true);
			 * setTitle(mPlanetTitles[position]);
			 * mDrawerLayout.closeDrawer(mDrawerList);
			 */
			break;
		case 2:
			// Intent i = new Intent(Listview.this, Listview.class);
			// startActivity(i);
			break;
		case 3:
			break;
		case 4:
			break;

		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:

			break;

		}

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

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

	/**
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	public static class PlanetFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public PlanetFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_planet,
					container, false);
			int i = getArguments().getInt(ARG_PLANET_NUMBER);
			String planet = getResources().getStringArray(
					R.array.navigation_array)[i];

			int imageId = getResources().getIdentifier(
					planet.toLowerCase(Locale.getDefault()), "drawable",
					getActivity().getPackageName());
			((ImageView) rootView.findViewById(R.id.image1))
					.setImageResource(imageId);
			getActivity().setTitle(planet);
			return rootView;
		}
	}

	public static class NewsfeedFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public NewsfeedFragment() {
			// Empty Constructor
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_listview,
					container, false);
			int i = getArguments().getInt(ARG_PLANET_NUMBER);
			String nav = getResources()
					.getStringArray(R.array.navigation_array)[i];

			getActivity().setTitle(nav);
			return rootView;
		}
	}

	public static class SearchFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public SearchFragment() {
			// Empty Constructor
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_listview,
					container, false);
			int i = getArguments().getInt(ARG_PLANET_NUMBER);
			String nav = getResources()
					.getStringArray(R.array.navigation_array)[i];

			getActivity().setTitle(nav);
			return rootView;
		}
	}

	public void DrawerLayout() {
		mTitle = mDrawerTitle = getTitle();
		mPlanetTitles = getResources().getStringArray(R.array.navigation_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mPlanetTitles));
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
