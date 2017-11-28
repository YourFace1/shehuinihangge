package bwie.jingdong.model;

import java.io.IOException;

import bwie.jingdong.bean.GjzBean;
import bwie.jingdong.presenter.GjzPre;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by Maibenben on 2017/11/7.
 */

public class GjzModelSx implements GjzModel {
    GjzPre gjzPre;

    public GjzModelSx(GjzPre gjzPre) {
        this.gjzPre = gjzPre;
    }

    @Override
    public void getData(String name) {
        String path="https://www.zhaoapi.cn/product/searchProducts?keywords="+name+"&page=1&source=android";
        OkHttp3Utils.doGet(path, new GsonObjectCallback<GjzBean>() {
            @Override
            public void onUi(GjzBean gjzBean) {
                gjzPre.setData(gjzBean);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
