package bwie.jingdong.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import bwie.jingdong.R;
import bwie.jingdong.bean.LoginBean;
import bwie.jingdong.presenter.LoginP;
import bwie.jingdong.presenter.LoginSx;
import bwie.jingdong.view.LoginV;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginV {


    private EditText log_phone;
    private EditText log_pwd;
    private TextView zhuce;
    private Button bt_login;
    private LoginP loginP;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = LoginActivity.this.getSharedPreferences("user", Context.MODE_PRIVATE);
        initView();
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegActivity.class));
            }
        });

        loginP = new LoginSx(this);
    }


    private void initView() {
        log_phone = (EditText) findViewById(R.id.log_phone);
        log_pwd = (EditText) findViewById(R.id.log_pwd);
        zhuce = (TextView) findViewById(R.id.zhuce);
        bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                String phone = log_phone.getText().toString();
                String pwd = log_pwd.getText().toString();
                Map<String,String> map=new HashMap<>();
                map.put("mobile",phone);
                map.put("password",pwd);
                loginP.setMap(map);
                break;
        }
    }

    @Override
    public void LoginData(LoginBean loginBean) {
        String code = loginBean.getCode();
        String msg = loginBean.getMsg();
        Log.i("qqqq",code);
        Log.i("eeee",msg);
        int uid = loginBean.getData().getUid();
        String token = loginBean.getData().getToken();
        sp.edit().putString("uid",uid+"").commit();
        sp.edit().putString("token",token).commit();
        if(code.equals("0")){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            sp.edit().putBoolean("flag",true).commit();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else{
            Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
        }

    }
    /*
        *监听返回按钮
        */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        LoginActivity.this.finish();

    }

}
