package com.one.duanone.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.one.duanone.R;

import java.io.InputStream;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/29  15:27.
 */
public class GlideUtils {

    private static float radius = 0f;

    /**
     * 普通图片
     *
     * @param context
     * @param url
     * @param image
     */
    public static void urlImage(Context context, String url, ImageView image) {
        if (url == null) {
            return;
        }
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
//        Glide.with(context).load(url).placeholder(R.drawable.ugc_tip_loading_essay_night).error(R.drawable.ic_nearby_empty_list_night).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
    }

    /**
     * 圆形图片,适配图片的最大圆,图片的最小尺寸为圆的直径
     *
     * @param context
     * @param url
     * @param image
     */
    public static void urlCircleImage(Context context, String url, ImageView image) {
        if (url == null) {
            return;
        }
        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
//        Glide.with(context).load(url).transform(new GlideCircleTransform(context)).placeholder(R.drawable.ugc_tip_loading_essay_night).error(R.drawable.ic_nearby_empty_list_night).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
    }

    /**
     * 圆角图片,
     *
     * @param context
     * @param url
     * @param round   圆角半径
     * @param image
     */
    public static void urlRoundImage(Context context, String url, int round, ImageView image) {
        if (url == null) {
            return;
        }
        radius = round;
        Glide.with(context).load(url).transform(new GlideRoundTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
//        Glide.with(context).load(url).transform(new GlideRoundTransform(context)).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ugc_tip_loading_essay_night).error(R.drawable.ic_nearby_empty_list_night).into(image);
    }


    /**
     * Created DKL 圆形工具
     *
     * @return
     * @parent
     */
    public static class GlideCircleTransform extends BitmapTransformation {

        private Context context;

        public GlideCircleTransform(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i1) {

            return cicleCrop(bitmapPool, bitmap);
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    /**
     * 圆形的图片
     *
     * @param pool
     * @param source
     * @return
     */
    private static Bitmap cicleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        //获取最终圆形的半径
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_4444);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_4444);
        }
        //把bitmap当做画布
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        //从squared中获取颜色,设置给Paint
        paint.setShader(new BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    /**
     * Created DKL 圆角工具类
     *
     * @return
     * @parent
     */
    public static class GlideRoundTransform extends BitmapTransformation {
        private Context context;

        public GlideRoundTransform(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i1) {
            Bitmap b = zoomBitmap(roundCrop(bitmapPool, bitmap));
            return addLogo(b, context);
        }

        @Override
        public String getId() {
            return getClass().getName();
        }

    }

    /**
     * Created DKL 生成圆角的Bitmap
     *
     * @return
     * @parent
     */
    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_4444);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_4444);
        }
        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        //抗锯齿
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    /**
     * 缩放bitmap
     *
     * @param bitmap
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap) {

        Matrix matrix = new Matrix();
        matrix.postScale(0.7f, 0.7f);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        return bitmap;
    }

    /**
     * 加水印
     *
     * @param bitmap
     * @return
     */
    public static Bitmap addLogo(Bitmap bitmap, Context context) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon, options);
        int size = 100;
        int height = bmp.getHeight() * size / bmp.getWidth();
        options.outWidth = 100;
        options.outHeight = height;
        options.inJustDecodeBounds = false;
        Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon, options);

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int lw = logo.getWidth();
        int lh = logo.getHeight();

        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(newb);
        canvas.drawBitmap(bitmap, 0, 0, null);

        Paint paint = new Paint();
        paint.setAlpha(50);

        canvas.drawBitmap(logo, w - lw + 5, h - lh + 5, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);//保存
        canvas.restore();//储存
        return newb;
    }

}
