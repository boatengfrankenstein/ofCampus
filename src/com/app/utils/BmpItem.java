package com.app.utils;

import android.content.ClipData.Item;
import android.graphics.Bitmap;

public class BmpItem extends Item {

	public Bitmap bitmap;
	public String string;
	
	
	public BmpItem(Bitmap bitmap, String string) {
		super(string);
		this.bitmap = bitmap;
		this.string = string;
	}
	
	public Bitmap getImage() {
		return this.bitmap;
		
	}
}


