package com.acuratechglobal.bulkbilling.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class ImageUtils {

    public static final int REQUEST_CAMERA = 1;
    public static final int REQUEST_GALLERY = 2;
    static String JPEG_FILE_PREFIX="IMG_";
    static String JPEG_FILE_SUFFIX=".jpg";


    public static File setUpPhotoFile() throws IOException {
        File imageF = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {

            File storageDir = getAlbumDir();

            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }
            imageF = File.createTempFile(JPEG_FILE_PREFIX
                            + System.currentTimeMillis() + "_",
                    JPEG_FILE_SUFFIX, storageDir);
        } else {
            Log.v("Error-",
                    "External storage is not mounted READ/WRITE.");
        }

        return imageF;
    }

    private static File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

        /*storageDir = new File(Environment.getExternalStorageDirectory()+ UrlConstants.CAMERA_DIR
                + TAG);*/
            storageDir = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_PICTURES + "/");
            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }
        } else {
            Log.v("Error-", "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }

}
