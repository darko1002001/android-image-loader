package com.seavus.android.imageloader;


import android.widget.ImageView;

import com.seavus.android.imageloader.queue.DequeStrategy;
import com.seavus.android.imageloader.queue.QueueStrategy;
import com.seavus.android.imageloader.queue.StackStrategy;


/**
 * @author darko.grozdanovski
 */
public class PhotosQueue {

    final DequeStrategy<PhotoToLoad> photosToLoad;

    public enum QueueMethod {
        STACK, QUEUE;
    }

    public PhotosQueue(QueueMethod method) {
        super();
        if (method == QueueMethod.STACK) {
            photosToLoad = new StackStrategy<PhotoToLoad>();
            return;
        }
        if (method == QueueMethod.QUEUE) {
            photosToLoad = new QueueStrategy<PhotoToLoad>();
            return;
        }
        throw new IllegalArgumentException("Input a valid dequeue method, See " + QueueMethod.class.getName());
    }

    // removes all instances of this ImageView
    public void clean(ImageView image) {
        for (int j = 0; j < photosToLoad.size();) {
            if (photosToLoad.get(j).imageView == image) {
                photosToLoad.remove(j);
            } else {
                ++j;
            }
        }
    }

    // Task for the queue
    static class PhotoToLoad {

        public String url;
        public ImageView imageView;
        public final int photoSizeInPixels;

        public PhotoToLoad(String u, ImageView i, int photoSizeInPixels) {
            url = u;
            imageView = i;
            this.photoSizeInPixels = photoSizeInPixels;
        }
    }
}