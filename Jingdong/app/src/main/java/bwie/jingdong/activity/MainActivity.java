package bwie.jingdong.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import bwie.jingdong.Fragment.Fragment1;
import bwie.jingdong.Fragment.Fragment2;
import bwie.jingdong.Fragment.Fragment3;
import bwie.jingdong.Fragment.Fragment4;
import bwie.jingdong.R;

public class MainActivity extends AppCompatActivity {

    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg;
    private FrameLayout lin;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        initView();
        getFF(new Fragment1());
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rb1:
                        getFF(new Fragment1());
                        break;
                    case R.id.rb2:
                        getFF(new Fragment2());
                        break;
                    case R.id.rb3:
                        getFF(new Fragment3());

                        break;
                    case R.id.rb4:
                        getFF(new Fragment4());
                        boolean flag = sp.getBoolean("flag", false);
                        if(flag==false){
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        }
                        //emptiy();
                        break;
                }

            }
        });


    }
    private void getFF(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.lin,fragment).commit();

    }

    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        lin = (FrameLayout) findViewById(R.id.lin);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
    }
}
