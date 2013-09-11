package edu.hfmp3.offline;

import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.R;
import edu.hfmp3.utils.Const;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentOffline extends Fragment{
	private static final String TAG = "FragmentOffline";
	FilterLog log = new FilterLog(TAG);
	InterfaceFragmentOffline callback;
	
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
		return v;
	}
}
