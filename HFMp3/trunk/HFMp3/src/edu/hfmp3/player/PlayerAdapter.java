package edu.hfmp3.player;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PlayerAdapter extends FragmentStatePagerAdapter{
	List<Fragment> list;
	public PlayerAdapter(FragmentManager fm){
		super(fm);
		list = new ArrayList<Fragment>();
	}
	
	public void addF(Fragment f){
		list.add(f);
		notifyDataSetChanged();
	}

	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		return list.size();
	}
}
