package com.dg.libs.android.imageloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * @author Darko.Grozdanovski
 * @author Jan.Marincek
 **/
class BitmapContentHandler extends ContentHandler {

    /** Default value */
    private int defaultImageSize = 75;
    private final File tempFile;

    public BitmapContentHandler(int defaultImageSize, File tempFile) {
        super();
        this.defaultImageSize = defaultImageSize;
        this.tempFile = tempFile;
    }

    @Override
    public Bitmap getContent(URLConnection connection) throws IOException {
        //
        InputStream input = connection.getInputStream();
        input = new BlockingFilterInputStream(input);

        final FileOutputStream fileOutput = new FileOutputStream(tempFile);
        final byte[] buffer = new byte[512];
        int bufferLength = 0; // used to store a temporary size of the
        // buffer
        while ((bufferLength = input.read(buffer)) > 0) {
            // add the data in the buffer to the file in the file output
            // stream (the file on the sd card
            fileOutput.write(buffer, 0, bufferLength);
        }
        input.close();
        fileOutput.flush();
        fileOutput.close();

        Options decodeInBounds = Utils.decodeInBounds(tempFile, defaultImageSize);
        Bitmap bitmap = BitmapFactory.decodeFile(tempFile.getPath(), decodeInBounds);
        if (bitmap == null) {
            throw new IOException("Image could not be decoded");
        }
        return bitmap;
    }

}
