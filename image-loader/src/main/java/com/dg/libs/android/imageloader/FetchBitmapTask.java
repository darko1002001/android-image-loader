package com.dg.libs.android.imageloader;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * @author darko.grozdanovski
 */
final class FetchBitmapTask extends AsyncTask<Void, Void, Bitmap> {

    private final ImageLoader imageLoader;
    private final ImageLoaderListener listener;
    private final int size;
    private final String url;

    FetchBitmapTask(final ImageLoader imageLoader, final ImageLoaderListener listener, final int size, final String url) {
        this.imageLoader = imageLoader;
        this.listener = listener;
        this.size = size;
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(final Void... params) {
        return this.imageLoader.downloadBitmap(url, size);
    }

    @Override
    protected void onPostExecute(final Bitmap result) {
        if (result != null) {
            listener.onImageLoadingComplete(url, result);
            return;
        }
        listener.onImageLoadingError();

    }
}