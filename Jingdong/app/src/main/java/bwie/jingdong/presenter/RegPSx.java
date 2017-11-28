package bwie.jingdong.presenter;

import java.util.Map;

import bwie.jingdong.bean.RegBean;
import bwie.jingdong.model.RegM;
import bwie.jingdong.model.RegMSx;
import bwie.jingdong.view.RegV;

/**
 * Created by Maibenben on 2017/11/3.
 */

public class RegPSx implements RegP{

    private final RegM regMSx;
    RegV regV;

    public RegPSx(RegV regV) {
        this.regV = regV;
        regMSx =new RegMSx(this);
    }

    @Override
    public void setRegData(RegBean regBean) {
        regV.regData(regBean);
    }

    @Override
    public void setMap(Map<String, String> map) {
        regMSx.getRegData(map);
    }
}
