package com.zhangtao.volleyseverything;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by zhangtao on 16/2/5.
 * 主要是ImageRequest的用法和加载网络图片
 */
public class ImageRequestDemoActivity extends Activity {
    private ImageView img;
    private EditText inputArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagerequest);

        initViews();
    }

    private void initViews() {
        inputArea = (EditText) findViewById(R.id.inputArea);
        img = (ImageView) findViewById(R.id.getArea);
    }

    public void postImg(View view) {
        //1.同样的一个Activity可能要创建一个请求队列.
        RequestQueue mQueue = Volley.newRequestQueue(this);
        //2.创建一个ImageRequest对象.参数有
        //前三个和String一样.后面的第三第四个参数分别用于指定允许图片最大的宽度和高度，
        // 如果指定的网络图片的宽度或高度大于这里的最大值，则会对图片进行压缩，指定成0
        // 的话就表示不管图片有多大，都不会进行压缩。第五个参数用于指定图片的颜色属性，
        // Bitmap.Config下的几个常量都可以在这里使用，其中ARGB_8888可以展示最好的
        // 颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小
        //一共六个参数



        //Test:http://pica.nipic.com/2007-11-09/200711912453162_2.jpg
        String url = "http://" + inputArea.getText();
        Response.Listener listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                img.setImageBitmap(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("VolleyError", volleyError.getMessage());
            }
        };

        ImageRequest imageRequest = new ImageRequest(url, listener, 0, 0,
                Bitmap.Config.ARGB_8888, errorListener);
        //添加到请求队列.
        mQueue.add(imageRequest);

    }

    public void clearImg(View view) {
        inputArea.setText("");
        img.setImageBitmap(null);
    }
    /**
     * Cache
     * Cache的应用
     */
    /**
     * public class BitmapCache implements ImageCache {

     private LruCache<String, Bitmap> mCache;

     public BitmapCache() {
     int maxSize = 10 * 1024 * 1024;
     mCache = new LruCache<String, Bitmap>(maxSize) {
     @Override
     protected int sizeOf(String key, Bitmap bitmap) {
     return bitmap.getRowBytes() * bitmap.getHeight();
     }
     };
     }

     @Override
     public Bitmap getBitmap(String url) {
     return mCache.get(url);
     }

     @Override
     public void putBitmap(String url, Bitmap bitmap) {
     mCache.put(url, bitmap);
     }

     }

     ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());  
     */

}
