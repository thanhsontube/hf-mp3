package edu.hfmp3.drawer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sonnt_commonandroid.utils.FilterLog;

import edu.hfmp3.R;
import edu.hfmp3.utils.Const;
/**
 * adapter drawer layer
 * @author sonnt
 *
 */
public class AdapterDrawerMain extends ArrayAdapter<DtoDrawerMain>{
	private static final String TAG = "AdapterDrawerMain";
	Context context;
	List<DtoDrawerMain> list;
	FilterLog log = new FilterLog(TAG);
	
	
	public AdapterDrawerMain(Context context, List<DtoDrawerMain> list){
		super(context, 0, list);
		this.context = context;
		this.list = list;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
//		log.d("NECVN>>>" + "getview");
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
			if(dto.isSelected){
				v.setBackgroundResource(R.drawable.layer_drawer);
			}else{
				v.setBackgroundColor(android.R.color.transparent);
			}
		} else {
			holder.image.setVisibility(View.GONE);
			holder.txt.setTextColor(context.getResources().getColor(R.color.red1));
			v.setBackgroundResource(R.drawable.layer_bottom);
			v.setFocusable(true);
			v.setFocusableInTouchMode(true);
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
	
	public void update(){
		log.d("NECVN>>>" + "updated");
		notifyDataSetChanged();
	}
	
	
}
