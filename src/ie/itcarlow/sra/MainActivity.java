package ie.itcarlow.sra;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends DrawerActivity {

	SessionManager session;

	public class newsfeed {
		String username;
		String recommendation_detail;
	}

	newsFeedAdapter newsListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);

		session = new SessionManager(getApplicationContext());
		Toast.makeText(getApplicationContext(),
				"User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG)
				.show();
		
		session.checkLogin();
		newsListAdapter = new newsFeedAdapter();

		ListView codeLearnLessons = (ListView) findViewById(R.id.news_feed);
		codeLearnLessons.setAdapter(newsListAdapter);

		codeLearnLessons.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				newsfeed recent_update = newsListAdapter.getRecentUpdate(arg2);
				Toast.makeText(MainActivity.this, recent_update.username,
						Toast.LENGTH_LONG).show();
			}
		});

	}

	public class newsFeedAdapter extends BaseAdapter {
		List<newsfeed> newsfeedList = getDataForListView();

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return newsfeedList.size();
		}

		@Override
		public newsfeed getItem(int arg0) {
			// TODO Auto-generated method stub
			return newsfeedList.get(arg0);

		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if (arg1 == null) {
				LayoutInflater inflater = (LayoutInflater) MainActivity.this
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				arg1 = inflater.inflate(R.layout.listitem, arg2, false);
			}

			TextView chapterName = (TextView) arg1
					.findViewById(R.id.feed_text1);
			TextView chapterDesc = (TextView) arg1
					.findViewById(R.id.feed_text2);

			newsfeed recent_update = newsfeedList.get(arg0);

			chapterName.setText(recent_update.username);
			chapterDesc.setText(recent_update.recommendation_detail);

			return arg1;
		}

		public newsfeed getRecentUpdate(int position) {
			return newsfeedList.get(position);
		}

		public List<newsfeed> getDataForListView() {
			List<newsfeed> newsfeedList = new ArrayList<newsfeed>();

			for (int i = 0; i < 10; i++) {

				newsfeed recent_update = new newsfeed();
				recent_update.username = "Chapter " + i;
				recent_update.recommendation_detail = "This is description for chapter "
						+ i;
				newsfeedList.add(recent_update);
			}

			return newsfeedList;

		}

	}

}