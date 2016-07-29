package com.one.duanone.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/29  15:27.
 */
public class GlideUtils {

    private static float radius = 0f;

    /**
     * 普通图片
     * @param context
     * @param url
     * @param image
     */
    public static void urlImage(Context context, String url, ImageView image) {

        Glide.with(context).load(url).into(image);
    }

    /**
     * 圆形图片,适配图片的最大圆,图片的最小尺寸为圆的直径
     * @param context
     * @param url
     * @param image
     */
    public static void urlCircleImage(Context context,String url ,ImageView image){
        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).into(image);
    }

    /**
     * 圆角图片,
     * @param context
     * @param url
     * @param round 圆角半径
     * @param image
     */
    public static void urlRoundImage(Context context,String url,int round,ImageView image){
        radius = round;
        Glide.with(context).load(url).transform(new GlideRoundTransform(context)).into(image);
    }

    /**
     * Created DKL 圆形工具
     * @return 
     * @parent 
     */
    public static class GlideCircleTransform extends BitmapTransformation{


        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i1) {
            return cicleCrop(bitmapPool,bitmap);
        }

        @Override
        public String getId() {
            return null;
        }
    }

    /**
     * 圆形的图片
     * @param pool
     * @param source
     * @return
     */
    private static Bitmap cicleCrop(BitmapPool pool,Bitmap source){
        if (source==null)return null;
        //获取最终圆形的半径
        int size = Math.min(source.getWidth(),source.getHeight());
        int x = (source.getWidth() - size)/2;
        int y = (source.getHeight() - size)/2;

        Bitmap squared = Bitmap.createBitmap(source,x,y,size,size);

        Bitmap result = pool.get(size,size, Bitmap.Config.ARGB_8888);
        if (result == null){
            result = Bitmap.createBitmap(size,size,Bitmap.Config.ARGB_8888);
        }
        //把bitmap当做画布
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        //从squared中获取颜色,设置给Paint
        paint.setShader(new BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r= size/2f;
        canvas.drawCircle(r,r,r,paint);
        return result;
    }

    /**
     * Created DKL 圆角工具类
     * @return 
     * @parent 
     */
    public static class  GlideRoundTransform extends BitmapTransformation {

    public GlideRoundTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i1) {
        return roundCrop(bitmapPool,bitmap);
    }

    @Override
    public String getId() {
        return null;
    }

    }
    /**
     * Created DKL 生成圆角的Bitmap
     * @return 
     * @parent 
     */
    private static Bitmap roundCrop(BitmapPool pool,Bitmap source){
        if (source == null) return null;
        Bitmap result = pool.get(source.getWidth(),source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result ==null){
            result = Bitmap.createBitmap(source.getWidth(),source.getHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //抗锯齿
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f,0f,source.getWidth(),source.getHeight());
        canvas.drawRoundRect(rectF,radius,radius,paint);
        return result;
    }

}
