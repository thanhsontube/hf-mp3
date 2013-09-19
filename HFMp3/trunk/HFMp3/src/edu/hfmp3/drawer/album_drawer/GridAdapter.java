package edu.hfmp3.drawer.album_drawer;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.hfmp3.R;
import edu.hfmp3.utils.Const;
import edu.hfmp3.utils.UrlCommon;

public class GridAdapter extends ArrayAdapter<GridDto>{
	Context context;
	List<GridDto> list;
	int type;
	ImageLoader imageLoader;
	public GridAdapter(Context context, List<GridDto> list, int type){
		super(context, 0, list);
		this.context = context;
		this.list    = list;
		this.type = type;
		imageLoader = ImageLoader.getInstance();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Holder holder = null;
		if(v == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_grid, null);
			holder = new Holder();
			holder.image = (ImageView) v.findViewById(R.id.imgGridRow);
			holder.txt = (TextView) v.findViewById(R.id.txtGridRow);
			v.setTag(holder);
		}else{
			holder = (Holder) v.getTag();
		}
		GridDto dto = list.get(position);
		holder.txt.setText(dto.title);
		String url = UrlCommon.URLS[position];
		switch (type) {
		case Const.TYPE_VN:
			// holder.image.setBackgroundResource(R.drawable.pop);
			imageLoader.displayImage(url, holder.image);
			break;
		case Const.TYPE_QUOCTE:
			// holder.image.setBackgroundResource(R.drawable.rock);
			imageLoader.displayImage(url, holder.image);
			break;
		case Const.TYPE_HOATAU:
			// holder.image.setBackgroundResource(R.drawable.hiphop);
			imageLoader.displayImage(url, holder.image);
			break;

		default:
			break;
		}
//		holder.image.setBackgroundResource(R.drawable.ic_launcher);
		
		return v;
	}
	static class Holder{
		ImageView image;
		TextView txt;
	}
}
