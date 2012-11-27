package com.seavus.android.imageloader;

import android.graphics.Bitmap;

/**
 * @author darko.grozdanovski
 */
public interface ImageLoaderListener {

    public void onImageLoadingComplete(String url, Bitmap bitmap);

    public void onImageLoadingError();

}
