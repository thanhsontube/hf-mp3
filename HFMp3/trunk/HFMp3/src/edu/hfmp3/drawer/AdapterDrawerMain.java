package edu.hfmp3.drawer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.hfmp3.R;
import edu.hfmp3.utils.Const;

public class AdapterDrawerMain extends ArrayAdapter<DtoDrawerMain>{
	Context context;
	List<DtoDrawerMain> list;
	
	
	public AdapterDrawerMain(Context context, List<DtoDrawerMain> list){
		super(context, 0, list);
		this.context = context;
		this.list = list;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Holder holder = null;
		if (v == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_drawer_main	, null);
			holder = new Holder();
			holder.image = (ImageView) v.findViewById(R.id.img_avatar_drawer);
			holder.txt = (TextView) v.findViewById(R.id.txt_title_drawer);
			v.setTag(holder);
		}else{
			holder = (Holder) v.getTag();
		}
		DtoDrawerMain dto = list.get(position);
		holder.txt.setText(dto.title);
		if(getItemViewType(position) == Const.TYPE_ITEM){
			holder.image.setVisibility(View.VISIBLE);
			holder.image.setImageResource(dto.image);
			v.setFocusable(false);
			v.setFocusableInTouchMode(false);
			v.setBackgroundResource(android.R.color.transparent);
		} else {
			holder.image.setVisibility(View.GONE);
			v.setFocusable(true);
			v.setFocusableInTouchMode(true);
			v.setBackgroundResource(R.color.orange1);
		}
		
		return v;
	}
	
	static class Holder{
		ImageView image;
		TextView txt;
	}
	
	@Override
	public int getItemViewType(int position) {
		DtoDrawerMain dto = list.get(position);
		return dto.isGroup ? Const.TYPE_GROUP : Const.TYPE_ITEM;
	}
	
	
}
