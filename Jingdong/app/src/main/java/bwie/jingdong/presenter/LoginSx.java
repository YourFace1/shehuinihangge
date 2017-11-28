package bwie.jingdong.presenter;

import java.util.Map;

import bwie.jingdong.bean.LoginBean;
import bwie.jingdong.model.LoginM;
import bwie.jingdong.model.LoginMSx;
import bwie.jingdong.view.LoginV;

/**
 * Created by Maibenben on 2017/11/6.
 */

public class LoginSx implements LoginP {

    LoginM loginM;
    LoginV loginV;

    public LoginSx(LoginV loginV) {
        this.loginV = loginV;
        loginM=new LoginMSx(this);
    }

    @Override
    public void setLoginData(LoginBean loginData) {
        loginV.LoginData(loginData);
    }

    @Override
    public void setMap(Map<String, String> map) {
loginM.getLoginData(map);
    }
}
