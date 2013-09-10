package edu.hfmp3.drawer;



public class DtoDrawerMain {
	public int image;
	public String title;
	public boolean isSelected;
	public boolean isGroup;
	public int mValue;
	
	public DtoDrawerMain() {
		super();
		mValue = 0;
		isGroup = false;
		isSelected = false;
	}

	public DtoDrawerMain(int image, String title, boolean isSelected) {
		super();
		this.image = image;
		this.title = title;
		this.isSelected = isSelected;
	}
	
	
}
