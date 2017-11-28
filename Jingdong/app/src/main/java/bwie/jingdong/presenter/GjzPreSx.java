package bwie.jingdong.presenter;

import bwie.jingdong.bean.GjzBean;
import bwie.jingdong.model.GjzModel;
import bwie.jingdong.model.GjzModelSx;
import bwie.jingdong.view.GjzView;

/**
 * Created by Maibenben on 2017/11/7.
 */

public class GjzPreSx implements GjzPre {

    GjzView gjzView;
    GjzModel gjzModel;

    public GjzPreSx(GjzView gjzView) {
        this.gjzView = gjzView;
        gjzModel=new GjzModelSx(this);
    }

    @Override
    public void setType(String name) {
        gjzModel.getData(name);
    }

    @Override
    public void setData(GjzBean gjzBean) {
        gjzView.GjzData(gjzBean);
    }
}
