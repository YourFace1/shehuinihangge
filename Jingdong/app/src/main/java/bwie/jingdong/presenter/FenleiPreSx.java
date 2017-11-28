package bwie.jingdong.presenter;

import bwie.jingdong.Listener.FenleiListener;
import bwie.jingdong.bean.FenleizhuBean;
import bwie.jingdong.model.FenleiModel;
import bwie.jingdong.model.FenleiModelSx;
import bwie.jingdong.view.FenleiView;

/**
 * Created by Maibenben on 2017/11/8.
 */

public class FenleiPreSx implements FenleiPre,FenleiListener{

    FenleiModel fenleiModel;
    FenleiView fenleiView;

    public FenleiPreSx(FenleiView fenleiView) {
        this.fenleiView = fenleiView;
        fenleiModel=new FenleiModelSx();
    }

    @Override
    public void re() {
        fenleiModel.getData(this);
    }

    @Override
    public void onSuccess(FenleizhuBean fenleiBean) {
        fenleiView.FenleiData(fenleiBean);
    }
}
