package com.linjiaxiaohai.rgb.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Meng on 16/5/7.
 */
public class BitmapUtils {

    public static boolean saveBitmap(Bitmap bitmap, String name) {
        if (bitmap == null || TextUtils.isEmpty(name)) {
            return false;
        }
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), name);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
