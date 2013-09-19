package edu.hfmp3.player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sonnt_commonandroid.utils.FilterLog;
import com.viewpagerindicator.LinePageIndicator;

import edu.hfmp3.R;

public class PlayerFragment extends Fragment{
	
	private static final String TAG = "PlayerFragment";
	ViewPager pagerPlayer;
	LinePageIndicator linePageIndicator;
	PlayerAdapter adapterPlayer;
	FilterLog log = new FilterLog(TAG);
	public static PlayerFragment newInstance(){
		PlayerFragment f = new PlayerFragment();
		return f;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		log.d("NECVN>>>" + "onCreate");
		View v = inflater.inflate(R.layout.fragment_player	, container, false);
		pagerPlayer = (ViewPager) v.findViewById(R.id.pagerPlayer);
		
		adapterPlayer = new PlayerAdapter(getFragmentManager());
		pagerPlayer.setAdapter(adapterPlayer);
		PlayerTab2Fragment f2 = PlayerTab2Fragment.newInstance();
		adapterPlayer.addF(f2);
		PlayerTab3Fragment f3 = PlayerTab3Fragment.newInstance();
		adapterPlayer.addF(f3);
		adapterPlayer.notifyDataSetChanged();
		linePageIndicator = (LinePageIndicator) v.findViewById(R.id.lineIndicator);
		linePageIndicator.setViewPager(pagerPlayer);
		return v;
	}
}
