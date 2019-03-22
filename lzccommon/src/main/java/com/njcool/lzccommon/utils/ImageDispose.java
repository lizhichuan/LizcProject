package com.njcool.lzccommon.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
import android.view.WindowManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageDispose {
    /**
     * 旋转图片
     *
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        ;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            Log.i("readPictureDegree", "orientation = " + orientation);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    private float curDegrees = 90;// 当前旋转度数

    /**
     * 获取bitmap尺寸缩放解决OOM问题
     *
     * @throws IOException
     */
    public static Bitmap convertBitmap(File file, int degree, int quality) throws IOException {
        Bitmap bitmap = null;
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
        BitmapFactory.decodeStream(fis, null, o);
        fis.close();
        final int REQUIRED_SIZE = quality;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize = scale;
        fis = new FileInputStream(file.getAbsolutePath());
        bitmap = BitmapFactory.decodeStream(fis, null, op);
        fis.close();
        if (degree != 0) {
            bitmap = rotaingImageView(degree, bitmap);
        }
//		Matrix mt = new Matrix();
//		mt.setRotate(degree);
//		Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0,
//				bitmap.getWidth(), bitmap.getHeight(), mt, true);
//		bitmap.recycle();
//		Bitmap bitmapScale = ThumbnailUtils.extractThumbnail(rotateBitmap, 200,
//				158);
//		//200*160(480)Bitmap bitmapScale = ThumbnailUtils.extractThumbnail(rotateBitmap, 280,220);
//		rotateBitmap.recycle();
        Log.i("ImageDispose", "width=" + bitmap.getWidth() + "   height=" + bitmap.getHeight());
        return bitmap;
    }

    public static Bitmap pressBitmap(File file, int degree, int quality) throws IOException {
        Bitmap bitmap = null;
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
        BitmapFactory.decodeStream(fis, null, o);
        fis.close();
        final int REQUIRED_SIZE = quality;
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE && height_tmp / 2 < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        CoolLogTrace.i("","ImageDispose-------------pressBitmap", "width=" + o.outWidth + "   height=" + o.outHeight + "  degree = " + degree + " quality=" + quality + " scale=" + scale);
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize = scale;
        fis = new FileInputStream(file.getAbsolutePath());
        bitmap = BitmapFactory.decodeStream(fis, null, op);
        fis.close();
        if (degree != 0) {
            bitmap = rotaingImageView(degree, bitmap);
        }
//		Matrix mt = new Matrix();
//		mt.setRotate(degree);
//		Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0,
//				bitmap.getWidth(), bitmap.getHeight(), mt, true);
//		bitmap.recycle();
//		Bitmap bitmapScale = ThumbnailUtils.extractThumbnail(rotateBitmap, 200,
//				158);
//		//200*160(480)Bitmap bitmapScale = ThumbnailUtils.extractThumbnail(rotateBitmap, 280,220);
//		rotateBitmap.recycle();

        CoolLogTrace.i("","ImageDispose===========pressBitmap", "width=" + bitmap.getWidth() + "   height=" + bitmap.getHeight());
        return bitmap;
    }

    public static void saveThumbBitmap(String path, Bitmap bitmap) {
        // TODO Auto-generated method stub
        try {
            FileOutputStream fos = new FileOutputStream(path);
            boolean b = bitmap.compress(CompressFormat.PNG, 100, fos);
            try {
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (b) {
                CoolLogTrace.i("","ImageDispose===========saveThumbBitmap", "缩略图保存成功");
            } else {
                CoolLogTrace.i("","ImageDispose===========saveThumbBitmap", "缩略图保存失败");
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void dealThumbBitmap(File file, String srcPath, String desPath) {
        // TODO Auto-generated method stub
        if (file != null && file.exists()) {
            String fileName = file.getName();
            Bitmap bitmap = null;
            int degree = ImageDispose.readPictureDegree(srcPath);
            Log.i("saveThumbBitmap", "degree = " + degree);
            try {
                bitmap = ImageDispose.pressBitmap(file, degree, 400);
                if (desPath == null) {
                    ImageDispose.saveThumbBitmap(srcPath, bitmap);
                } else {
                    ImageDispose.saveThumbBitmap(desPath + "/" + fileName, bitmap);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (bitmap != null) {
                bitmap.recycle();
                bitmap = null;
            }
        }
    }

    public static String dealThumbBitmap(Context context, String srcPath, float hh, float ww) {
        File srcFile = new File(srcPath);
        String desPath = "";
        String dir = CoolPublicMethod.getCacheDirectory(context) + "images/";
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }

//        String fileName = file.getName();
        Bitmap bitmap = null;
        int degree = ImageDispose.readPictureDegree(srcPath);
        Log.i("saveThumbBitmap", "degree = " + degree);
        bitmap = getimage(context, srcPath,hh,ww);
        desPath = dir + "/" + srcFile.getName();
        ImageDispose.saveThumbBitmap(desPath, bitmap);
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }

        return desPath;
    }

    /**
     * 根据质量压缩图片
     *
     * @param image
     * @return
     */
    private static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 根据大小压缩图片
     *
     * @param srcPath
     * @return
     */
    private static Bitmap getimage(Context context, String srcPath, float hh, float ww) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//        float hh = 800f;//这里设置高度为800f
//        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }
}
