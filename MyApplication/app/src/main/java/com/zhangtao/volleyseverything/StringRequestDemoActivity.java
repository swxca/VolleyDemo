package com.zhangtao.volleyseverything;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

/**
 * Created by zhangtao on 16/2/5.
 * RequestQueue是一个请求队列对象，它可以缓存所有的HTTP请求，
 * 然后按照一定的算法并发地发出这些请求。RequestQueue内部的设
 * 计就是非常合适高并发的，因此我们不必为每一次HTTP请求都创建一
 * 个RequestQueue对象，这是非常浪费资源的，基本上在每一个需要
 * 和网络交互的Activity中创建一个RequestQueue对象就足够了。
 */
public class StringRequestDemoActivity extends Activity {

    private TextView getArae;
    private EditText iniputArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stringrequest);

        initViews();
    }

    private void initViews() {
        getArae = (TextView) findViewById(R.id.getArea);
        iniputArea = (EditText) findViewById(R.id.inputArea);
    }

    public void post(View view) {
        // 1.首先获取一个RequestQueue对象(相同的还是JsonArrayRequest和JsonObjectRequest用户一致)
        RequestQueue mQueue = Volley.newRequestQueue(this);
        //2.创建一个StringRequest对象,这个对象有三个参数,url
        String url = "http://" + iniputArea.getText().toString();
        //返回后的处理逻辑,注意listener后面可以指定泛型为任意对象
        Response.Listener listener = new Response.Listener<Object>() {
            @Override
            public void onResponse(Object s) {
                getArae.setText((String) s);
            }
        };
        //返回错误的处理逻辑
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //得到返回错误的信息
                getArae.setText(volleyError.getMessage());
            }
        };
        StringRequest stringRequest = new StringRequest(url, listener, errorListener);

        //最后将这个StringRequest放入到请求队列里面
        mQueue.add(stringRequest);
        //最后记得声明访问网络的权限
    }


    public void clear(View view) {
        iniputArea.setText("");
        getArae.setText("");
    }

    //最后是url带参数的网络请求
    /**
     * StringRequest stringRequest = new StringRequest(Method.POST, url,  listener, errorListener) {
            @Override protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("params1", "value1");
            map.put("params2", "value2");
            return map;
    }
    };
     */
}
