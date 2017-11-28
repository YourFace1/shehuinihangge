package bwie.jingdong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import bwie.jingdong.R;
import bwie.jingdong.bean.RegBean;
import bwie.jingdong.presenter.RegP;
import bwie.jingdong.presenter.RegPSx;
import bwie.jingdong.view.RegV;

public class RegActivity extends AppCompatActivity implements View.OnClickListener,RegV {

    private EditText et_phone;
    private EditText et_pwd;
    private Button bt_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();


    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_reg = (Button) findViewById(R.id.bt_reg);

        bt_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_reg:
                String phone = et_phone.getText().toString();
                String pwd = et_pwd.getText().toString();
                RegP regPSx = new RegPSx(this);
                Map<String,String> map=new HashMap<>();
                map.put("mobile",phone);
                map.put("password",pwd);
                regPSx.setMap(map);
                break;
        }
    }


    @Override
    public void regData(RegBean regBean) {
        String code = regBean.getCode();
        if(code.equals("0")){
            Log.i("qqq",code);
            Toast.makeText(this, "注冊成功", Toast.LENGTH_SHORT).show();

        }
    }
}
