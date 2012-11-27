package com.seavus.android.imageloader;

import java.io.File;

import android.content.Context;
import android.os.Environment;

/**
 * @author Darko.Grozdanovski
 * @author Jan.Marincek
 **/
class FileCache {

    private static final String SDCARD_FOLDER = Environment.getExternalStorageDirectory() + "/Android/data/%s/files/";
    private static final String TEMP_CACHE = "/temp/";

    private static File cacheDir;

    public FileCache(Context context) {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = new File(getSdCacheLocation(context));

        } else {
            cacheDir = context.getCacheDir();
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
    }

    public static String getSdCacheLocation(Context context) {
        return String.format(SDCARD_FOLDER, context.getPackageName());
    }

    public static File getTempFile(String filename) {
        File f = new File(cacheDir + TEMP_CACHE);
        if (!f.exists()) {
            f.mkdirs();
        }
        return new File(f, MD5.md5(filename));
    }

    public File getFile(String url) {
        String filename = String.valueOf(MD5.md5(url));
        File f = new File(cacheDir, filename);
        return f;
    }

    public void clearCache() {
        File[] files = cacheDir.listFiles();
        for (File f : files) {
            try {
                f.delete();
            } catch (Exception e) {
            }
        }
    }

}
