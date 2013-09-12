package edu.hfmp3.offline.album;

public class AlbumDto {
	public int image;
	public String song;
	public String singer;
	public String title;
	public AlbumDto(int image, String song, String singer) {
		super();
		this.image = image;
		this.song = song;
		this.singer = singer;
	}
	public AlbumDto(String song, String singer) {
		super();
		this.song = song;
		this.singer = singer;
	}
	
}
