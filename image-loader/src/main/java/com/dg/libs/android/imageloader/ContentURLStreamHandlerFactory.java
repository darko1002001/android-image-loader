package com.dg.libs.android.imageloader;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import android.content.ContentResolver;

/**
 * @author Darko.Grozdanovski
 * @author Jan.Marincek
 * 
 * 
 * {@link URLStreamHandlerFactory} for {@code content://}, {@code file://}, and
 * {@code android.resource://} URIs.
 **/
class ContentURLStreamHandlerFactory implements URLStreamHandlerFactory {

    private final ContentResolver resolver;

    public ContentURLStreamHandlerFactory(ContentResolver resolver) {
        if (resolver == null) {
            throw new NullPointerException();
        }
        this.resolver = resolver;
    }

    /**
     * {@inheritDoc}
     */
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (ContentResolver.SCHEME_CONTENT.equals(protocol) || ContentResolver.SCHEME_FILE.equals(protocol)
                || ContentResolver.SCHEME_ANDROID_RESOURCE.equals(protocol)) {
            return new ContentURLStreamHandler(this.resolver);
        } else {
            return null;
        }
    }

}
