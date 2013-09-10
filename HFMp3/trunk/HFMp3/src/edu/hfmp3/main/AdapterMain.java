package edu.hfmp3.main;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.hfmp3.R;
import edu.hfmp3.drawer.DtoDrawerMain;
import edu.hfmp3.utils.Const;

public class AdapterMain extends ArrayAdapter<DtoDrawerMain>{
	private static final String TAG = "AdapterMain";
	Context context;
	List<DtoDrawerMain>list;
//	FilterLog log = new FilterLog(TAG);
	public AdapterMain(Context context, List<DtoDrawerMain> list){
		super(context, 0, list);
		this.context = context;
		this.list = list;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Holder holder = null;
		int type = getItemViewType(position);
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_main, null);
			holder = new Holder();
			holder.image = (ImageView) v.findViewById(R.id.img_avatar_main);
			holder.txt = (TextView) v.findViewById(R.id.txt_title_main);
			holder.count = (TextView) v.findViewById(R.id.txt_count_main);
			v.setTag(holder);
		} else {
			holder = (Holder) v.getTag();
		}
		DtoDrawerMain dto = list.get(position);
		holder.txt.setText(dto.title);
		if(type == Const.TYPE_ITEM){
			if(dto.mValue > 0) {
				holder.count.setText(String.valueOf(dto.mValue));
			}
			holder.image.setImageResource(dto.image);
			holder.image.setVisibility(View.VISIBLE);
			holder.count.setVisibility(View.VISIBLE);
			v.setBackgroundResource(android.R.color.transparent);
		}else{
			holder.image.setVisibility(View.GONE);
			holder.count.setVisibility(View.GONE);
			
			v.setBackgroundResource(R.color.orange1);
		}
		return v;
	}
	
	@Override
	public int getItemViewType(int position) {
		DtoDrawerMain dto = list.get(position);
		return dto.isGroup ? Const.TYPE_GROUP : Const.TYPE_ITEM;
	}
	static class Holder{
		ImageView image;
		TextView txt;;
		TextView count;
	}
}
