package edu.hfmp3.drawer.album_drawer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import edu.hfmp3.R;
import edu.hfmp3.utils.Const;

public class GridFragment extends Fragment{
	Context context;
	GridAdapter gridAdapter;
	GridAdapter gridAdapter2;
	GridAdapter gridAdapter3;
	List<GridDto> listGrid;
	List<GridDto> listGrid2;
	List<GridDto> listGrid3;
	ExpandableHeightGridView gridView;
	ExpandableHeightGridView gridView2;
	ExpandableHeightGridView gridView3;
	View llGrid;
	View llGrid2;
	View llGrid3;
	
	public static GridFragment newInstance(){
		GridFragment f = new GridFragment();
		return f;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_grid, container, false);
		gridView = (ExpandableHeightGridView) v.findViewById(R.id.gridVideo);
		gridView2 = (ExpandableHeightGridView) v.findViewById(R.id.gridVideo2);
		gridView3 = (ExpandableHeightGridView) v.findViewById(R.id.gridVideo3);
		gridView.setExpanded(true);
		gridView2.setExpanded(true);
		gridView3.setExpanded(true);
		llGrid = v.findViewById(R.id.llGrid);
		llGrid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		llGrid2 = v.findViewById(R.id.llGrid2);
		llGrid2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		llGrid3 = v.findViewById(R.id.llGrid3);
		llGrid3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		init();
		init2();
		init3();
		gridAdapter = new GridAdapter(getActivity(), listGrid,Const.TYPE_VN);
		gridAdapter2 = new GridAdapter(getActivity(), listGrid2,Const.TYPE_QUOCTE);
		gridAdapter3 = new GridAdapter(getActivity(), listGrid3,Const.TYPE_HOATAU);
		gridView.setAdapter(gridAdapter);
		gridView2.setAdapter(gridAdapter2);
		gridView3.setAdapter(gridAdapter3);
		return v;
	}
	public void init(){
		listGrid = new ArrayList<GridDto>();
		listGrid.add(new GridDto("Nhac tre", 0));
		listGrid.add(new GridDto("Nhac tru tinh", 1));
		listGrid.add(new GridDto("Rock Viet", 2));
		listGrid.add(new GridDto("Rap Viet", 3));
		listGrid.add(new GridDto("Nhac Dance", 4));
		listGrid.add(new GridDto("Nhac Que Huong", 5));
		listGrid.add(new GridDto("Nhac Thieu Nhi", 6));
		listGrid.add(new GridDto("Nhac Trinh", 7));
		listGrid.add(new GridDto("Nhac Khong Loi", 8));
		listGrid.add(new GridDto("Nhac Phim", 9));
	}
	public void init2(){
		listGrid2 = new ArrayList<GridDto>();
		listGrid2.add(new GridDto("Pop", 0));
		listGrid2.add(new GridDto("ROCK", 1));
		listGrid2.add(new GridDto("Hiphop", 2));
		listGrid2.add(new GridDto("Country", 3));
		listGrid2.add(new GridDto("Electronic / Dance", 4));
		listGrid2.add(new GridDto("R&B", 5));
		listGrid2.add(new GridDto("Audiophile", 5));
		listGrid2.add(new GridDto("OST", 5));
		listGrid2.add(new GridDto("Han Quoc", 5));
		listGrid2.add(new GridDto("Nhat Ban", 5));
		listGrid2.add(new GridDto("Hoa Ngu", 5));
		listGrid2.add(new GridDto("Gospel", 5));
		listGrid2.add(new GridDto("Indie", 5));
		listGrid2.add(new GridDto("Trance/house", 5));
		listGrid2.add(new GridDto("Jazz", 5));
		listGrid2.add(new GridDto("New Age", 5));
		listGrid2.add(new GridDto("Folk", 5));
		listGrid2.add(new GridDto("Classical", 5));
		
	}
	
	public void init3(){
		listGrid3 = new ArrayList<GridDto>();
		listGrid3.add(new GridDto("Piano", 0));
		listGrid3.add(new GridDto("Guitar", 0));
		listGrid3.add(new GridDto("Violin", 0));
		listGrid3.add(new GridDto("Nhac cu dan toc", 0));
		listGrid3.add(new GridDto("Cello", 0));
		listGrid3.add(new GridDto("World music", 0));
		listGrid3.add(new GridDto("Saxophone", 0));
		listGrid3.add(new GridDto("Nhac cu khac", 0));
		
	}
}
