package com.dg.libs.android.imageloader.sample;

import android.app.Application;
import android.content.Context;

import com.dg.libs.android.imageloader.ImageLoader;
import com.dg.libs.android.imageloader.sample.R;

public class DemoApp extends Application {

    public static final String TAG = DemoApp.class.getSimpleName();

    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        imageLoader = createImageLoader(this);
    }

    @Override
    public Object getSystemService(String name) {
        if (ImageLoader.IMAGE_LOADER_SERVICE.equals(name)) {
            return imageLoader;
        } else {
            return super.getSystemService(name);
        }
    }

    private ImageLoader createImageLoader(Context context) {
        final ImageLoader imageLoader = new ImageLoader(context, R.drawable.loading);
        imageLoader.setDefaultBitmapSize(75);
        imageLoader.setLoadingErrorDrawableId(R.drawable.error);
        imageLoader.setLoggerStatus(true);
        return imageLoader;
    }

}
