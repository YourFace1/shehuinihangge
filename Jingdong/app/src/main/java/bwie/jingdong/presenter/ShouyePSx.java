package bwie.jingdong.presenter;

import bwie.jingdong.Listener.ShouyeListener;
import bwie.jingdong.bean.ShouyeBean;
import bwie.jingdong.model.ShouyeM;
import bwie.jingdong.model.ShouyeMSx;
import bwie.jingdong.view.ShouyeView;

/**
 * Created by Maibenben on 2017/11/2.
 */

public class ShouyePSx implements ShouyeP,ShouyeListener {

    ShouyeView shouyeView;
    private final ShouyeM shouyeMSx;

    public ShouyePSx(ShouyeView shouyeView) {
        this.shouyeView = shouyeView;
        shouyeMSx =  new ShouyeMSx();
    }

    @Override
    public void setData() {
        shouyeMSx.getdata(this);
    }

    @Override
    public void onSuccess(ShouyeBean shouyeBean) {
        shouyeView.ShouyeData(shouyeBean);
    }
}
