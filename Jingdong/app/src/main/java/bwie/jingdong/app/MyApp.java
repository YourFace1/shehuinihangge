package bwie.jingdong.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/9/8 12:33
 */

public class MyApp extends Application {
    private static SharedPreferences sharedPreferences = null;

    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }
    public static MyApp getInstance() {
        return mInstance;
    }
    //SharedPreferences单例模式
    public static SharedPreferences getSharedPreferencesInstance(Context context){
        if (sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

}
