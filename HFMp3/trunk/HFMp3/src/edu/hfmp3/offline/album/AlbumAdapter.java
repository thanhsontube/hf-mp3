package edu.hfmp3.offline.album;

import java.util.List;

import edu.hfmp3.R;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumAdapter extends ArrayAdapter<AlbumDto>{
	List<AlbumDto> list;
	Context context;
	public AlbumAdapter(Context context, List<AlbumDto> list){
		super(context, 0, list);
		this.context = context;
		this.list = list;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Holder holder = null;
		if(v == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_album	, null);
			holder = new Holder();
			holder.imgAvatar = (ImageView) v.findViewById(R.id.imgAvatarAlbum);
			holder.imgMore = (ImageView) v.findViewById(R.id.imgMoreAlbum);
			holder.txtSinger = (TextView) v.findViewById(R.id.txtSingerAlbum);
			holder.txtSong = (TextView) v.findViewById(R.id.txtSongAlbum);
			holder.imgMore.setOnClickListener(clickListener);
			v.setTag(holder);
		}else{
			holder = (Holder) v.getTag();
		}
		AlbumDto dto = list.get(position);
		holder.imgAvatar.setImageResource(R.drawable.music_2);
		holder.txtSinger.setText(dto.singer);
		holder.txtSong.setText(dto.song);
		return v;
	}
	
	OnClickListener clickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			popupMenu(v);
		}
	};
	
	static class Holder{
		ImageView imgAvatar;
		TextView txtSong;
		TextView txtSinger;
		ImageView imgMore;
	}
	
	public void popupMenu(View v){
		PopupMenu popupMenu = new PopupMenu(context, v);
		popupMenu.inflate(R.menu.popup_album);
		popupMenu.show();
	}
}
