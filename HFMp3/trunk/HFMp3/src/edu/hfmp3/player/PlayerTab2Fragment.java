package edu.hfmp3.player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sonnt_commonandroid.utils.FileUtils;
import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.R;

public class PlayerTab2Fragment extends Fragment{
	private static final String TAG = "PlayerTab2Fragment";
	ImageView imgDics;
	TextView txtLyrics;
	FilterLog log = new FilterLog(TAG);
	RotateAnimation anim;
	boolean isPause = false;
	static PlayerTab2Fragment newInstance(){
		PlayerTab2Fragment f = new PlayerTab2Fragment();
		return f;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_player_tab2, container, false);
		imgDics = (ImageView) v.findViewById(R.id.img_player_tab2_dics);
		imgDics.setOnClickListener(onPlayer2Click);
		anim = new RotateAnimation(0f, 360f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				                0.5f);
		anim.setInterpolator(new LinearInterpolator());
		anim.setRepeatCount(Animation.INFINITE);
		anim.setDuration(4000);

		// Start animating the image
		imgDics.startAnimation(anim);
		
		txtLyrics = (TextView) v.findViewById(R.id.txt_player_tab2_lyrics);
		txtLyrics.setText(FileUtils.getStringFromAsset(getActivity(), "BestOfMe.txt"));
		return v;
	}
	
	OnClickListener onPlayer2Click = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.img_player_tab2_dics:
				if(imgDics.getAnimation() != null){
					imgDics.setAnimation(null);
				}else{
					imgDics.startAnimation(anim);
				}
				
				break;

			default:
				break;
			}
		}
	};
}
