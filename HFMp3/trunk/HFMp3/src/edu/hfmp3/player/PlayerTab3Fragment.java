package edu.hfmp3.player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sonnt_commonandroid.utils.FileUtils;
import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.R;

public class PlayerTab3Fragment extends Fragment{
	private static final String TAG = "PlayerTab3Fragment";
	TextView txtLyrics;
	FilterLog log = new FilterLog(TAG);
	static PlayerTab3Fragment newInstance(){
		PlayerTab3Fragment f = new PlayerTab3Fragment();
		return f;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_player_tab3, container, false);
		
		txtLyrics = (TextView) v.findViewById(R.id.txt_player_tab3_lyrics);
		txtLyrics.setText(FileUtils.getStringFromAsset(getActivity(), "BestOfMe.txt"));
		return v;
	}
}
