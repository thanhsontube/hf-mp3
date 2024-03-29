package edu.hfmp3.main;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.R;
import edu.hfmp3.drawer.DtoDrawerMain;
import edu.hfmp3.utils.Const;

public class FragmentMain extends Fragment{
	private static final String TAG = "FragmentMain";
	ListView listview;
	AdapterMain adapter;
	List<DtoDrawerMain> list;
	View header;
	InterfaceFragmentMain callback;
	FilterLog log = new FilterLog(TAG);
	
	public interface InterfaceFragmentMain{
		public void onItemClick(int pos,String s);
	}
	
	public static FragmentMain newInstance(int num){
		FragmentMain f = new FragmentMain();
		Bundle bundle = new Bundle();
		bundle.putInt(Const.NUM, num);
		f.setArguments(bundle);
		return f;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		log.d("NECVN>>>" + "onCreate");
		super.onCreate(savedInstanceState);
	}
	
	public void setOnFragmentMain(InterfaceFragmentMain callback){
		this.callback = callback;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		log.d("NECVN>>>" + "onCreateView");
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		listview = (ListView) v.findViewById(R.id.listviewMain);
		init();
		adapter = new AdapterMain(getActivity(), list);
		header = getActivity().getLayoutInflater().inflate(R.layout.header_main, null);
		listview.addHeaderView(header);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(onItemClickListener);
		return v;
	}
	
	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			log.d("NECVN>>>" + "onItemClick pos:" + arg2 + ";title:" + list.get(arg2).title);
			callback.onItemClick(arg2, list.get(arg2).title);
			
		}
	};
	
	public void init() {
		list = new ArrayList<DtoDrawerMain>();
		int[] arrDrawer = new int[] { R.drawable.music_2, R.drawable.album, R.drawable.artist, R.drawable.playlist,
				R.drawable.folder, R.drawable.download, R.drawable.catalog, R.drawable.yeuthich, R.drawable.playlist ,
				R.drawable.upload};
		
		
		DtoDrawerMain dto = new DtoDrawerMain();
		//group
		dto.title = "Nhac Offline";
		dto.isGroup = true;
		list.add(dto);
		
		
		dto = new DtoDrawerMain();
		dto.title = "Bai hat";
		dto.isSelected = false;
		dto.mValue = 15;
		list.add(dto);

		
		dto = new DtoDrawerMain();
		dto.title = "Album";
		dto.mValue = 6;
		list.add(dto);
		
		
		dto = new DtoDrawerMain();
		dto.title = "Nghe Si";
		dto.mValue = 1;
		list.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Playlist";
		list.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Thu Muc";
		dto.mValue = 6;
		list.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Download";
		list.add(dto);
		
		
		
		//group
		dto = new DtoDrawerMain();
		dto.title = "Nhac Online";
		dto.isGroup = true;
		list.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Nhac Theo Chu De";
		list.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Yeu thich";
		dto.mValue = 1;
		list.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Playplist";
		dto.mValue = 1;
		list.add(dto);
		
		dto = new DtoDrawerMain();
		dto.title = "Nhac upload";
		dto.mValue = 1;
		list.add(dto);

		int i = 0;
		for (DtoDrawerMain item : list) {
			if(!item.isGroup){
				item.image = arrDrawer[i];
				i++;
			}
		}
	}
}
