package com.zhangtao.volleyseverything;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by zhangtao on 16/2/5.
 * ImageLoader也可以用于加载网络上的图片，并且它的内部也是使用
 * ImageRequest来实现的，不过ImageLoader明显要比ImageRequest
 * 更加高效，
 * A:可以帮我们对图片进行缓存
 * B:可以过滤掉重复的链接,避免重复发送请求。
 */
public class ImageLoaderActivity extends Activity {
    /**
     * ImageLoader
     * ImageLoader的用法
     * 1.依然还是创建一个RequestQueue对象
     * 2.创建一个ImageLoader对象
     * 3.获取一个ImageListener对象。
     * 4.调用ImageLoader的get()方法加载网络上的图片
     *
     */

    private EditText inputArea;
    private ImageView getArea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader);

        initViews();
    }

    private void initViews() {
        inputArea= (EditText) findViewById(R.id.inputArea);
        getArea= (ImageView) findViewById(R.id.getArea);
    }

    public void postImgImageLoader(View view){
        //1.额 ....毫无疑问首先还是拿到这个拿了无数遍的请求队列
        RequestQueue mQueue= Volley.newRequestQueue(this);
        //2.创建一个ImageLoader,请一定非常注意那第二个参数,这是ImageLoader
        //的强大之处,这是ImageLoader的缓存机制.一会详细讲.
        ImageLoader imageLoader=new ImageLoader(mQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String s) {
                return null;
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {

            }
        });
        //3.获取一个ImageListener
        ImageLoader.ImageListener listener=ImageLoader.getImageListener(
                getArea,R.drawable.touxiangzhihu,R.drawable.ic_menu_emoticons);
        //4.imageLoader.get
        //get方法还有一个四个参数的重载方法,用来指定图片的宽和高
        imageLoader.get("http://"+inputArea.getText().toString(),listener);

    }
    public void clearImgloader(View view){
        inputArea.setText("");
        getArea.setImageBitmap(null);
    }
}
