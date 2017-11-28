package bwie.jingdong.model;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import bwie.jingdong.Api.Api;
import bwie.jingdong.bean.LoginBean;
import bwie.jingdong.presenter.LoginP;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by Maibenben on 2017/11/6.
 */

public class LoginMSx implements LoginM{
    LoginP loginP;

    public LoginMSx(LoginP loginP) {
        this.loginP = loginP;
    }


    @Override
    public void getLoginData(Map<String, String> map) {
        OkHttp3Utils.doPost(Api.denglu, map, new GsonObjectCallback<LoginBean>() {
            @Override
            public void onUi(LoginBean loginBean) {
                String msg = loginBean.getMsg();
                Log.i("msg",msg);
                loginP.setLoginData(loginBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
