package bwie.jingdong.presenter;

import java.util.Map;

import bwie.jingdong.bean.LoginBean;

/**
 * Created by Maibenben on 2017/11/6.
 */

public interface LoginP {
    void setLoginData(LoginBean loginData);
    void setMap(Map<String,String> map);
}
