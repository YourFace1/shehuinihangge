package bwie.jingdong.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.app.MyApp;
import bwie.jingdong.bean.Addaddress;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

public class AdressActivity extends AppCompatActivity {

    private EditText et_sita_addr;
    private EditText et_sita_mobile;
    private EditText et_sita_name;
    private Button bt_sita_tijiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress);
        et_sita_addr = (EditText) findViewById(R.id.et_sita_addr);
        et_sita_mobile = (EditText) findViewById(R.id.et_sita_mobile);
        et_sita_name = (EditText) findViewById(R.id.et_sita_name);
        bt_sita_tijiao = (Button) findViewById(R.id.bt_sita_tijiao);
        bt_sita_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();

            }
        });
    }
    private void getData(){
        String s = et_sita_addr.getText().toString();
        String s1 = et_sita_mobile.getText().toString();
        String s2 = et_sita_name.getText().toString();

        SharedPreferences preferences = MyApp.getSharedPreferencesInstance(this);
        String uid = preferences.getString("uid", "");
        String token = preferences.getString("token", "");

        Map<String,String> mapSita = new HashMap<>();
        mapSita.put("uid",uid);
        mapSita.put("addr",s);
        mapSita.put("mobile",s1);
        mapSita.put("name",s2);
        mapSita.put("token",token);

        OkHttp3Utils.doPost(Api.ADDaddress, mapSita, new GsonObjectCallback<Addaddress>() {
            @Override
            public void onUi(Addaddress sitaBean) {
                if(sitaBean.getCode().equals("0")){
                    Toast.makeText(AdressActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(AdressActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
