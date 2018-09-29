package com.example.petya.photogallery;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;
import android.util.Log;

public class ImageCache extends LruCache<String, Bitmap> {

    public ImageCache(int maxSize) {
        super(maxSize);
    }

    public Bitmap getBitmapFromMemory(String key) {
        return this.get(key);
    }

    public void setBitmapToMemory(String key, Bitmap bitmap) {
        if (getBitmapFromMemory(key) == null) {
            this.put(key, bitmap);
            Log.d("ImageCache", key + " добавлен в кэш");
        }
    }
}
