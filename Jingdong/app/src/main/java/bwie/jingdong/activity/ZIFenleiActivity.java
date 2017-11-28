package bwie.jingdong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.adapter.XqAdapter;
import bwie.jingdong.bean.FenleisanBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

public class ZIFenleiActivity extends AppCompatActivity {

    private RecyclerView recyc_xq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zifenlei);
        recyc_xq = (RecyclerView) findViewById(R.id.recyc_xq);
        recyc_xq.setLayoutManager(new LinearLayoutManager(this));

        final Intent intent=getIntent();
        String pscid = intent.getStringExtra("pscid");
        //Toast.makeText(this, "pscid", Toast.LENGTH_SHORT).show();
        OkHttp3Utils.doGet(Api.fenlei3 + pscid+"&source=android", new GsonObjectCallback<FenleisanBean>() {

            private List<FenleisanBean.DataBean> data;

            @Override
            public void onUi(FenleisanBean fenleisanBean) {
                data = fenleisanBean.getData();
                XqAdapter xqAdapter = new XqAdapter(ZIFenleiActivity.this, data);
                recyc_xq.setAdapter(xqAdapter);
                xqAdapter.setOnItemClickListener(new XqAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int id) {
                        Log.i("TAG",id+"aaaaa");
                        Intent in=new Intent(ZIFenleiActivity.this,XqActivity.class);
                        in.putExtra("id",id+"");
                        startActivity(in);
                    }
                });

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
