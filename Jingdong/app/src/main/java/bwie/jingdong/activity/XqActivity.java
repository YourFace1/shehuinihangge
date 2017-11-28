package bwie.jingdong.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.alpay.PayDemoActivity;
import bwie.jingdong.bean.AddShopCarBean;
import bwie.jingdong.bean.XqBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

public class XqActivity extends AppCompatActivity implements View.OnClickListener {

    private XqBean.DataBean datas;
    private ImageView xq_back;
    private ImageView xq_iv;
    private TextView xq_tv1;
    private TextView xq_tv2;
    private TextView xq_tv3;
    private TextView xq_tv4;
    private TextView xq_tv5;
    private Button xq_shopcar;
    private Button xq_buynow;

    private String id;
    private int i=1;
    private Map<String,String> carMap;
    private String uid;
    private  String token;
    private int goods_id;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xq);
        initView();

        //获得传过来的数据
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        //返回
        initBack();
        //获取数据
        initGetData();
    }
    private void initPopuowindow() {
        View view = View.inflate(XqActivity.this, R.layout.popupwindow_shop, null);
        PopupWindow pop=new PopupWindow(view,700,300);
        pop.setContentView(view);
        pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        pop.setFocusable(true);
        pop.setTouchable(true);


        View inflate = LayoutInflater.from(XqActivity.this).inflate(R.layout.activity_xq, null);
        pop.showAtLocation(inflate, Gravity.BOTTOM,0,0);
        TextView pop_tv1 = view.findViewById(R.id.pop_tv1);
        TextView pop_tv2 = view.findViewById(R.id.pop_tv2);
        ImageView pop_iv = view.findViewById(R.id.pop_iv);

        Picasso.with(XqActivity.this).load(s).into(pop_iv);
        pop_tv1.setText(datas.getTitle());
        pop_tv2.setText("¥ "+datas.getPrice());

        //点击加减
        Button pop_jia = view.findViewById(R.id.pop_jia);
        Button pop_jian = view.findViewById(R.id.pop_jian);
        final TextView pop_tv3 = view.findViewById(R.id.text3);
        pop_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                pop_tv3.setText(i+"");
            }
        });
        pop_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i==1){

                }else{
                    i--;
                    pop_tv3.setText(i+"");
                }
            }
        });

        //添加购物车
        final Button addshopcar = view.findViewById(R.id.addshopcar);


        addshopcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
                uid = sp.getString("uid", uid);
                token = sp.getString("token", token);

                carMap=new HashMap<>();
                carMap.put("uid", uid);
                carMap.put("pid",goods_id+"");
                carMap.put("token",token);
                //carMap.put("quantity",i+"");

                OkHttp3Utils.doPost(Api.ADDSHOPCAR, carMap, new GsonObjectCallback<AddShopCarBean>() {
                    @Override
                    public void onUi(AddShopCarBean addShopCarBean) {

                        String code = addShopCarBean.getCode();
                        //Log.i("qqq",datas);
                        if(code.equals("0")){
                            Toast.makeText(XqActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(XqActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });

    }


    private void initGetData() {
        OkHttp3Utils.doGet(Api.fenleiXq+id, new GsonObjectCallback<XqBean>() {
            @Override
            public void onUi(XqBean xqBean) {
                datas = xqBean.getData();
                //获取添加购物车ID
                goods_id = datas.getPid();
                xq_iv.setScaleType(ImageView.ScaleType.FIT_XY);
                String images = xqBean.getData().getImages();
                String[] split = images.split("!");
                s = split[0];
                Picasso.with(XqActivity.this).load(s).into(xq_iv);
                xq_tv1.setText(datas.getTitle());
                xq_tv2.setText(datas.getCreatetime());
                xq_tv3.setText(datas.getSubhead());
                xq_tv4.setText("¥ "+ datas.getPrice()+"");
                xq_tv5.setText(datas.getDetailUrl());

                /*WebSettings settings = web.getSettings();
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setJavaScriptEnabled(true);
                web.setWebChromeClient(new WebChromeClient());
                web.setWebViewClient(new WebViewClient());
                web.loadUrl(API.INTRODUCED_PATH+"&goods_id="+id);*/
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    private void initBack() {
        xq_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XqActivity.this.finish();
            }
        });
    }
    private void initView() {
        xq_back = (ImageView) findViewById(R.id.xq_back);
        xq_iv = (ImageView) findViewById(R.id.xq_iv);
        xq_tv1 = (TextView) findViewById(R.id.xq_tv1);
        xq_tv2 = (TextView) findViewById(R.id.xq_tv2);
        xq_tv3 = (TextView) findViewById(R.id.xq_tv3);
        xq_tv4 = (TextView) findViewById(R.id.xq_tv4);
        xq_tv5 = (TextView) findViewById(R.id.xq_tv5);
        xq_shopcar = (Button) findViewById(R.id.xq_shopcar);
        xq_buynow = (Button) findViewById(R.id.xq_buynow);

        xq_shopcar.setOnClickListener(this);
        xq_buynow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.xq_shopcar:

                initPopuowindow();
                break;
            case R.id.xq_buynow:

                startActivity(new Intent(XqActivity.this,PayDemoActivity.class));
                break;
        }
    }
}
