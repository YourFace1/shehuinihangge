package bwie.jingdong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.alpay.PayDemoActivity;
import bwie.jingdong.bean.DingBean;
import bwie.jingdong.bean.HuoAddrBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

public class DingdanActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ding_iv;
    private TextView name_tv;
    private TextView price_tv;
    private TextView ding_price;
    private Button din_ti;
    private Button ding_jie;
    private String uid;
    private String token;
    private String price;
    private Map<String, String> map,momap;
    private LinearLayout zhi;
    private LinearLayout wei;
    private TextView address;
    private String addr;
    private long mobile;
    private String name;
    private TextView setmoren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        initView();
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        token = intent.getStringExtra("token");
        price = intent.getStringExtra("price");
        Log.i("jiage1", price);
        Log.i("TAG", token);
        Log.i("uuu", uid);

        map = new HashMap<>();
        map.put("uid", uid);
        map.put("token", token);
        map.put("price", price);
        ding_price.setText(price);

        //获取地址
        momap=new HashMap<>();
        momap.put("uid", uid);
        momap.put("token", token);
        getMoRen();

    }

    private void getMoRen(){
        OkHttp3Utils.doPost(Api.MORENaddress, momap, new GsonObjectCallback<HuoAddrBean>() {
            @Override
            public void onUi(HuoAddrBean huoAddrBean) {
                HuoAddrBean.DataBean data = huoAddrBean.getData();
                addr = data.getAddr();
                mobile = data.getMobile();
                name = data.getName();
                Log.i("nnn",name);

                price_tv.setText(mobile+"");
                name_tv.setText(name);
                address.setText(addr);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
    private void initView() {
        name_tv = (TextView) findViewById(R.id.name_tv);
        price_tv = (TextView) findViewById(R.id.price_tv);
        address = (TextView) findViewById(R.id.address);
        ding_price = (TextView) findViewById(R.id.ding_price);
        ding_price.setOnClickListener(this);
        ding_jie = (Button) findViewById(R.id.ding_jie);
        ding_jie.setOnClickListener(this);
        setmoren = (TextView) findViewById(R.id.setmoren);

        setmoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DingdanActivity.this,FindAddrActivity.class));
            }
        });

    }

    public void getPay() {
        View view = View.inflate(DingdanActivity.this, R.layout.pay_item, null);
        PopupWindow pop = new PopupWindow(view, 700, 300);
        pop.setContentView(view);
        pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        pop.setFocusable(true);
        pop.setTouchable(true);


        View inflate = LayoutInflater.from(DingdanActivity.this).inflate(R.layout.activity_dingdan, null);
        pop.showAtLocation(inflate, Gravity.BOTTOM, 0, 0);

        zhi = (LinearLayout) view.findViewById(R.id.zhi);
        wei = (LinearLayout) view.findViewById(R.id.wei);
        zhi.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {
            startActivity(new Intent(DingdanActivity.this, PayDemoActivity.class));
        }
        });

        /*wei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DingdanActivity.this, WeixinActivity.class));
            }
        });*/

    }

    @Override
    public void onClick(View view) {
        if (view == ding_jie) {

            OkHttp3Utils.doPost(Api.DINGDAN, map, new GsonObjectCallback<DingBean>() {
                @Override
                public void onUi(DingBean dingBean) {
                    String code = dingBean.getCode();
                    Log.i("TAG", code);
                    if (code.equals("0")) {
                        Toast.makeText(DingdanActivity.this, "提交成功", Toast.LENGTH_SHORT).show();


                        //startActivity(new Intent(DingdanActivity.this, PayDemoActivity.class));
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

            getPay();
        }
    }
}
