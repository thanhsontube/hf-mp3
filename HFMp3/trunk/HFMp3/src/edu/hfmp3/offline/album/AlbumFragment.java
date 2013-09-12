package edu.hfmp3.offline.album;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.utils.Const;

public class AlbumFragment extends ListFragment{
	private static final String TAG = "AlbumFragment";
	AlbumAdapter adapter;
	List<AlbumDto> list;
	FilterLog log = new FilterLog(TAG);
	public String title;
	
	public static AlbumFragment newInstance(String title){
		AlbumFragment f = new AlbumFragment();
		Bundle bundle = new Bundle();
		bundle.putString(Const.NUM, title);
		f.setArguments(bundle);
		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		log.d("NECVN>>>" + "onCreate");
		super.onCreate(savedInstanceState);
		if(getArguments() != null){
			title = getArguments().getString(Const.NUM);
		}
		init();
		adapter = new AlbumAdapter(getActivity(), list);
		setListAdapter(adapter);
	}
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		log.d("NECVN>>>" + "onCreateView");
//		init();
//		adapter = new AlbumAdapter(getActivity(), list);
////		getListView().setAdapter(adapter);
//		return super.onCreateView(inflater, container, savedInstanceState);
//	}
	public void init(){
		log.d("NECVN>>>" + "init");
		list = new ArrayList<AlbumDto>();
		AlbumDto dto = new AlbumDto("Con se khong  quen", "Duy Manh");
		list.add(dto);
		
		dto = new AlbumDto("What makes you beautiful", "One Direction");
		list.add(dto);
		
		dto = new AlbumDto("Samsung", "Samsung");
		list.add(dto);
		
		dto = new AlbumDto("Con se khong  quen", "Duy Manh");
		list.add(dto);
		
		dto = new AlbumDto("What makes you beautiful", "One Direction");
		list.add(dto);
		
		dto = new AlbumDto("Samsung", "Samsung");
		list.add(dto);
		
		dto = new AlbumDto("Con se khong  quen", "Duy Manh");
		list.add(dto);
		
		dto = new AlbumDto("What makes you beautiful", "One Direction");
		list.add(dto);
		
		dto = new AlbumDto("Samsung", "Samsung");
		list.add(dto);
		
	}
}
