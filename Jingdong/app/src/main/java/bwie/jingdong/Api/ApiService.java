package bwie.jingdong.Api;

import bwie.jingdong.bean.FenleiBean;
import bwie.jingdong.bean.UserBean;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Maibenben on 2017/11/7.
 */

public interface ApiService  {
    //http://120.27.23.105/user/getUserInfo
    @GET("user/getUserInfo")
    Call<UserBean> getNoParams();

    //https://www.zhaoapi.cn/product/getProductCatagory
    @GET("product/getProductCatagory")
    Call<FenleiBean> getFenlei();
}
