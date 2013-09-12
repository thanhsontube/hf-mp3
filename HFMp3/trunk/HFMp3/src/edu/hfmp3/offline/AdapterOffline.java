package edu.hfmp3.offline;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.offline.album.AlbumFragment;

public class AdapterOffline extends FragmentStatePagerAdapter{
	private static final String TAG = "AdapterOffline";
	List<Fragment> list;
	FilterLog log = new FilterLog(TAG);
	public AdapterOffline(FragmentManager fm){
		super(fm);
		log.v("NECVN>>>" + "AdapterOffline");
		list = new ArrayList<Fragment>();
	}
	
	public void addTab(Fragment f){
		log.d("NECVN>>>" + "addTab");
		list.add(f);
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int arg0) {
		log.i("NECVN>>>" + "getItem:" + arg0);
		Fragment f = list.get(arg0);
		return f;
	}

	@Override
	public int getCount() {
		return list.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		AlbumFragment f = (AlbumFragment) list.get(position);
		return f.title;
	}
}
