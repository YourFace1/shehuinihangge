package bwie.jingdong.model;

import java.io.IOException;

import bwie.jingdong.Api.Api;
import bwie.jingdong.Listener.ShouyeListener;
import bwie.jingdong.bean.ShouyeBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by Maibenben on 2017/11/2.
 */

public class ShouyeMSx implements ShouyeM{
    @Override
    public void getdata(final ShouyeListener listener) {
        OkHttp3Utils.doGet(Api.shouye, new GsonObjectCallback<ShouyeBean>() {
            @Override
            public void onUi(ShouyeBean shouyeBean) {

                listener.onSuccess(shouyeBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
