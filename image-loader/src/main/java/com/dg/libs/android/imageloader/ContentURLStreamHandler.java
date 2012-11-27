package com.dg.libs.android.imageloader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import android.content.ContentResolver;

/**
 * @author Darko.Grozdanovski
 * @author Jan.Marincek
 * 
 * {@link URLStreamHandler} for {@code content://}, {@code file://}, and {@code android.resource://}
 * URIs.
 **/
class ContentURLStreamHandler extends URLStreamHandler {

    private final ContentResolver resolver;

    public ContentURLStreamHandler(ContentResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException();
        }
        this.resolver = resolver;
    }

    @Override
    protected URLConnection openConnection(URL url) {
        return new ContentURLConnection(resolver, url);
    }

}
