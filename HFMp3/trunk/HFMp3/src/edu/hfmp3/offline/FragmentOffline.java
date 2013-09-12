package edu.hfmp3.offline;

import com.example.sonnt_commonandroid.utils.FilterLog;
import com.viewpagerindicator.TitlePageIndicator;

import edu.hfmp3.R;
import edu.hfmp3.offline.album.AlbumFragment;
import edu.hfmp3.utils.Const;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentOffline extends Fragment{
	private static final String TAG = "FragmentOffline";
	FilterLog log = new FilterLog(TAG);
	InterfaceFragmentOffline callback;
	
	ViewPager pager;
	AdapterOffline adapter;
	static FragmentManager fm;
	
	//titlepage indicator
	TitlePageIndicator indicator;
	
	
	public interface InterfaceFragmentOffline{
		public void onItemClick(String s);
	}
	
	public static FragmentOffline newInstance(int num){
		FragmentOffline f = new FragmentOffline();
		Bundle bundle = new Bundle();
		bundle.putInt(Const.NUM, num);
		f.setArguments(bundle);
		return f;
	}
	
	public static FragmentOffline newInstance(int num, FragmentManager fm){
		FragmentOffline f = new FragmentOffline();
		Bundle bundle = new Bundle();
		bundle.putInt(Const.NUM, num);
		f.setArguments(bundle);
		FragmentOffline.fm = fm;
		return f;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		log.d("NECVN>>>" + "onCreate");
		Bundle bundle = getArguments();
		if(bundle != null){
			int num = bundle.getInt(Const.NUM);
			
			log.d("NECVN>>>" + "num:" + num);
		}
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		log.d("NECVN>>>" + "onCreateView");
		View v = inflater.inflate(R.layout.fragment_offline, container, false);
		pager = (ViewPager) v.findViewById(R.id.pager);
		indicator = (TitlePageIndicator) v.findViewById(R.id.indicator);
		adapter = new AdapterOffline(getFragmentManager());
		pager.setAdapter(adapter);
		
		AlbumFragment albumFragment= AlbumFragment.newInstance("Playlist");
		adapter.addTab(albumFragment);
		
		AlbumFragment albumFragment1= AlbumFragment.newInstance("Bai Hat");
		adapter.addTab(albumFragment1);
		
		AlbumFragment albumFragment2= AlbumFragment.newInstance("Album");
		adapter.addTab(albumFragment2);
		
		AlbumFragment albumFragment3= AlbumFragment.newInstance("Nghe Si");
		adapter.addTab(albumFragment3);
		
		pager.setOffscreenPageLimit(adapter.getCount());
		initIndicator();
		
		
		return v;
	}
	
	public void initIndicator(){
		indicator.setViewPager(pager);
		indicator.setBackgroundColor(getResources().getColor(R.color.orange1));
		indicator.setTextColor(Color.WHITE);
		indicator.setSelectedBold(true);
		indicator.setSelectedColor(Color.BLACK);
	}
}
