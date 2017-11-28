package bwie.jingdong.model;

import java.io.IOException;

import bwie.jingdong.Api.Api;
import bwie.jingdong.Listener.FenleiListener;
import bwie.jingdong.bean.FenleizhuBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by Maibenben on 2017/11/8.
 */

public class FenleiModelSx implements FenleiModel{
    @Override
    public void getData(final FenleiListener fenleiListener) {

        /*//创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.fenlei).addConverterFactory(GsonConverterFactory.create()).build();
        //通过动态代理的方式网络接口对象
        ApiService apiService = retrofit.create(ApiService.class);
        //得到call对象
        Call<FenleiBean> call = apiService.getFenlei();
        //执行异步请求
       call.enqueue(new Callback<FenleiBean>() {
           @Override
           public void onResponse(Call<FenleiBean> call, Response<FenleiBean> response) {
               List<FenleiBean.DataBean> data = response.body().getData();
               String name = data.get(0).getList().get(1).getName();
               Log.i("rrr",name);
                //fenleiListener.onSuccess();
           }

           @Override
           public void onFailure(Call<FenleiBean> call, Throwable t) {

           }
       });*/

        OkHttp3Utils.doGet(Api.fenlei1, new GsonObjectCallback<FenleizhuBean>() {
            @Override
            public void onUi(FenleizhuBean fenleiBean) {
                fenleiListener.onSuccess(fenleiBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
