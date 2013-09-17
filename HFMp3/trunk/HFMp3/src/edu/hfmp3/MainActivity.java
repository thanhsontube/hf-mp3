package edu.hfmp3;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.drawer.AdapterDrawerMain;
import edu.hfmp3.drawer.DtoDrawerMain;
import edu.hfmp3.drawer.album_drawer.GridFragment;
import edu.hfmp3.main.FragmentMain;
import edu.hfmp3.main.FragmentMain.InterfaceFragmentMain;
import edu.hfmp3.offline.FragmentOffline;

public class MainActivity extends ActionBarActivity {
	private static final String TAG = "MainActivity";
	ActionBar actionBar;
	ActionBarDrawerToggle toggle;
	DrawerLayout drawerLayout;
	ListView listviewDrawer;
	List<DtoDrawerMain> listDrawer;
	AdapterDrawerMain adapterDrawer;
	Context context;
	FilterLog log = new FilterLog(TAG);
	SearchView searchView;
	boolean isnavigatorDrawer = true;
	
	//fragment 
	FragmentMain fragmentMain;
	FragmentOffline fragmentOffline;
	GridFragment gridFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.d("NECVN>>>" + "onCreate");
        setContentView(R.layout.activity_main);
        context = this;
        
        
        //drawerlayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.open, R.string.close){

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				actionBar.setTitle("My Music");
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				actionBar.setTitle("Menu");
			}
        	
        };
        drawerLayout.setDrawerListener(toggle);
        
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        
        listviewDrawer = (ListView) findViewById(R.id.listview_drawer);
        listviewDrawer.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listviewDrawer.setOnItemClickListener(itemDrawerClick);
        initDrawer();
        adapterDrawer = new AdapterDrawerMain(context, listDrawer);
        listviewDrawer.setAdapter(adapterDrawer);
        
        fragmentMain = FragmentMain.newInstance(0);
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragmentMain).commit();
        //item on main screen click
        fragmentMain.setOnFragmentMain(new InterfaceFragmentMain() {
        	
        	@Override
        	public void onItemClick(int pos, String s) {
        		log.d("NECVN>>>" + "click at pos:" + pos + ";s:" + s);
        		isnavigatorDrawer = false;
        		pos = pos -1;
        		fragmentOffline = FragmentOffline.newInstance(pos, getSupportFragmentManager());
        		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame	, fragmentOffline).addToBackStack(null).commit();
        		actionBar.setDisplayHomeAsUpEnabled(true);
        		if(android.os.Build.VERSION.SDK_INT >= 11){
        			invalidateOptionsMenu();  //call onPrepareOptionsMenu
        		}else{
        			updatemenu();
        		}
        	}
        });
        handleIntent(getIntent());
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
    	log.d("NECVN>>>" + "onPrepareOptionsMenu");
    	if(isnavigatorDrawer){
    		toggle.setDrawerIndicatorEnabled(true);
    	}else{
    		toggle.setDrawerIndicatorEnabled(false);
    	}
    	
		return true;
    	
    };
    //when item on navigator drawer click
    OnItemClickListener itemDrawerClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			log.d("NECVN>>>" + "drawe click :" + arg2);
//			listviewDrawer.setItemChecked(arg2, true);
			
//			update list
			for (DtoDrawerMain dto : listDrawer) {
				dto.isSelected = false;
			}
			listDrawer.get(arg2).isSelected = true;
			adapterDrawer.notifyDataSetChanged();
			log.d("NECVN>>>" + "updated");
			if(arg2 == 0 || arg2 == 1){
				getSupportFragmentManager().popBackStack();
			}else{
				
				if(gridFragment == null){
					gridFragment = GridFragment.newInstance();
				}
				getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, gridFragment).addToBackStack(null).commit();
			}
			drawerLayout.closeDrawer(listviewDrawer);
		}
	};
    
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
//    		searchView.onActionViewCollapsed();
//    		searchView.setQuery("", false);
    	}
	}
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
    	toggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	log.d("NECVN>>>" + "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        return true;
    }
    
    public void updatemenu(){
    	if(isnavigatorDrawer){
    		toggle.setDrawerIndicatorEnabled(true);
    	}else{
    		toggle.setDrawerIndicatorEnabled(false);
    	}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(toggle.onOptionsItemSelected(item)){
    		return true;
    	}
    	switch (item.getItemId()) {
		case android.R.id.home:
//			Toast.makeText(getApplication(), "up", Toast.LENGTH_SHORT).show();
			getSupportFragmentManager().popBackStack();
			isnavigatorDrawer = true;
			updatemenu();
			break;

		default:
			break;
		}
    	return super.onOptionsItemSelected(item);
    }
    
	public void initDrawer() {
		listDrawer = new ArrayList<DtoDrawerMain>();
		int[] arrDrawer = new int[] { R.drawable.login, R.drawable.music_2, R.drawable.album, R.drawable.video,
				R.drawable.artist, R.drawable.top, R.drawable.catalog, R.drawable.setting };

		DtoDrawerMain dto = new DtoDrawerMain();
		dto.title = "Dang Nhap";
		dto.isSelected = false;
		listDrawer.add(dto);

		dto = new DtoDrawerMain();
		dto.title = "My Music";
		dto.isSelected = true;
		listDrawer.add(dto);
		
		//group
		dto = new DtoDrawerMain();
		dto.title = "SONNT MUSIC";
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
