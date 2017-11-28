package bwie.jingdong.model;

import java.io.IOException;
import java.util.Map;

import bwie.jingdong.Api.Api;
import bwie.jingdong.bean.RegBean;
import bwie.jingdong.presenter.RegP;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by Maibenben on 2017/11/3.
 */

public class RegMSx implements RegM {

     RegP regP;
    public RegMSx(RegP regP) {
        this.regP = regP;
    }

    @Override
    public void getRegData(Map<String, String> map) {
        OkHttp3Utils.doPost(Api.zhuce, map, new GsonObjectCallback<RegBean>() {
            @Override
            public void onUi(RegBean regBean) {
                regP.setRegData(regBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
