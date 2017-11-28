package bwie.jingdong.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bwie.jingdong.Api.Api;
import bwie.jingdong.Api.ApiService;
import bwie.jingdong.R;
import bwie.jingdong.activity.AdressActivity;
import bwie.jingdong.activity.FindAddrActivity;
import bwie.jingdong.activity.MainActivity;
import bwie.jingdong.bean.UserBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maibenben on 2017/10/31.
 */

public class Fragment4 extends Fragment implements View.OnClickListener {


    private ImageView tuichu;
    private TextView myusername;
    private TextView myaddress;
    private SharedPreferences sp;
    private TextView tuichudenglu;
    private TextView findaddress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        initView(view);
        findaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FindAddrActivity.class));
            }
        });
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        //用retrofit获取用户ID
        //getNoParams();

        boolean flag = sp.getBoolean("flag", false);
        if (flag == true) {
            String uid = sp.getString("uid", "");
            myusername.setText(uid);
        } else {
            myusername.setText("未登录");
        }


        return view;
    }

    private void getNoParams() {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.userdata).addConverterFactory(GsonConverterFactory.create()).build();
        //通过动态代理的方式网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        //得到call对象
        Call<UserBean> call = apiService.getNoParams();
        //执行异步请求
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                UserBean body = response.body();
                String msg = body.getMsg();
                String code = body.getCode();
                Log.i("wwww", msg + code);
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {

            }
        });


    }


    private void initView(View view) {
        tuichu = (ImageView) view.findViewById(R.id.tuichu);
        myusername = (TextView) view.findViewById(R.id.myusername);
        myaddress = (TextView) view.findViewById(R.id.myaddress);
        findaddress = view.findViewById(R.id.findaddress);
        myaddress.setOnClickListener(this);
        tuichudenglu = (TextView) view.findViewById(R.id.tuichudenglu);
        tuichudenglu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myaddress:

                startActivity(new Intent(getActivity(), AdressActivity.class));
                break;
            case R.id.tuichudenglu:
                sp.edit().clear().commit();
                startActivity(new Intent(getActivity(), MainActivity.class));

                break;
        }
    }


}
