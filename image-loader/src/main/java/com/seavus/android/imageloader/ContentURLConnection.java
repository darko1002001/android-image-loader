package com.seavus.android.imageloader;

import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;

/**
 * @author Darko.Grozdanovski
 * @author Jan.Marincek
 **/
class ContentURLConnection extends URLConnection {

    private final ContentResolver resolver;

    private final Uri uri;

    private InputStream inputStream;

    private OutputStream outputStream;

    private boolean connected;

    private boolean inputStreamClosed;

    private boolean outputStreamClosed;

    public ContentURLConnection(ContentResolver resolver, URL url) {
        super(url);
        this.resolver = resolver;
        String spec = url.toString();
        uri = Uri.parse(spec);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connect() throws IOException {
        if (getDoInput()) {
            InputStream in = this.resolver.openInputStream(uri);
            this.inputStream = new ContentURLConnectionInputStream(in);
        }
        if (getDoOutput()) {
            OutputStream out = this.resolver.openOutputStream(uri, "rwt");
            this.outputStream = new ContentURLConnectionOutputStream(out);
        }
        this.connected = true;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (this.inputStreamClosed) {
            throw new IllegalStateException("Closed");
        }
        if (!this.connected) {
            connect();
        }
        return this.inputStream;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        if (this.outputStreamClosed) {
            throw new IllegalStateException("Closed");
        }
        if (!this.connected) {
            connect();
        }
        return this.outputStream;
    }

    @Override
    public Object getContent() throws IOException {
        if (!this.connected) {
            connect();
        }
        return super.getContent();
    }

    @Override
    public String getContentType() {
        return this.resolver.getType(uri);
    }

    @Override
    public int getContentLength() {
        try {
            AssetFileDescriptor fd = this.resolver.openAssetFileDescriptor(uri, "r");
            long length = fd.getLength();
            if (length <= 0 && length <= Integer.MAX_VALUE) {
                return (int) length;
            }
        } catch (IOException e) {
        }
        return -1;
    }

    private class ContentURLConnectionInputStream extends FilterInputStream {

        public ContentURLConnectionInputStream(InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
            super.close();
            inputStreamClosed = true;
        }
    }

    private class ContentURLConnectionOutputStream extends FilterOutputStream {

        public ContentURLConnectionOutputStream(OutputStream out) {
            super(out);
        }

        @Override
        public void close() throws IOException {
            super.close();
            outputStreamClosed = true;
        }
    }

}
