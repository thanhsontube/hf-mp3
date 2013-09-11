package edu.hfmp3;

import java.util.ArrayList;
import java.util.List;

import com.example.sonnt_commonandroid.utils.FilterLog;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import edu.hfmp3.drawer.AdapterDrawerMain;
import edu.hfmp3.drawer.DtoDrawerMain;
import edu.hfmp3.main.FragmentMain;

public class MainActivity extends ActionBarActivity {
	private static final String TAG = "MainActivity";
	ActionBar actionBar;
	ActionBarDrawerToggle toggle;
	DrawerLayout drawerLayout;
	ListView listviewDrawer;
	List<DtoDrawerMain> listDrawer = new ArrayList<DtoDrawerMain>();
	AdapterDrawerMain adapterDrawer;
	Context context;
	FilterLog log = new FilterLog(TAG);
	SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.d("NECVN>>>" + "onCreate");
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.navigation_drawer_main);
        context = this;
        
        
        //drawerlayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.open, R.string.close){

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
        	
        };
        drawerLayout.setDrawerListener(toggle);
        
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        
        listviewDrawer = (ListView) findViewById(R.id.listview_drawer);
        listviewDrawer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listviewDrawer.setSelected(true);
        initDrawer();
        adapterDrawer = new AdapterDrawerMain(context, listDrawer);
        listviewDrawer.setAdapter(adapterDrawer);
        
        FragmentMain fragmentMain = new FragmentMain();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragmentMain).commit();
        handleIntent(getIntent());
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	log.d("NECVN>>>" + "onNewIntent");
    	super.onNewIntent(intent);
    	handleIntent(intent);
    }

	public void handleIntent(Intent intent) {
		log.d("NECVN>>>" + "handleIntent");
		if(Intent.ACTION_SEARCH.equals(intent.getAction())){
    		String query = intent.getStringExtra(SearchManager.QUERY);
    		log.d("NECVN>>>" + "query:" + query);
    		
    		SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, SearchProvider.AUTHEN, SearchProvider.MODE);
    		suggestions.saveRecentQuery(query, null);
    		searchView.onActionViewCollapsed();
    		searchView.setQuery("", false);
    	}
	}
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
    	toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(toggle.onOptionsItemSelected(item)){
    		return true;
    	}
    	switch (item.getItemId()) {
		case android.R.id.home:
			Toast.makeText(getApplication(), "up", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
	public void initDrawer() {
		int[] arrDrawer = new int[] { R.drawable.login, R.drawable.music_2, R.drawable.album, R.drawable.video,
				R.drawable.artist, R.drawable.top, R.drawable.category, R.drawable.setting };

		DtoDrawerMain dto = new DtoDrawerMain();
		dto.title = "Dang Nhap";
		dto.isSelected = false;
		listDrawer.add(dto);

		dto = new DtoDrawerMain();
		dto.title = "My Music";
		listDrawer.add(dto);
		
		//group
		dto = new DtoDrawerMain();
		dto.title = "ZING MP3";
		dto.isGroup = true;
		listDrawer.add(dto);
		
		
		dto = new DtoDrawerMain();
		dto.title = "Album";
		listDrawer.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Video Clip";
		listDrawer.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Nghe Si";
		listDrawer.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Top 100";
		listDrawer.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Nhac Theo Chu De";
		listDrawer.add(dto);
		
		//group
		dto = new DtoDrawerMain();
		dto.title = "CONG CU";
		dto.isGroup = true;
		listDrawer.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Cai Dat";
		listDrawer.add(dto);

		int i = 0;
		for (DtoDrawerMain item : listDrawer) {
			if(!item.isGroup){
				item.image = arrDrawer[i];
				i++;
			}
		}
	}
    
}
