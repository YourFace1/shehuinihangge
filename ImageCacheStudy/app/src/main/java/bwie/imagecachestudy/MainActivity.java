package bwie.imagecachestudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import bwie.imagecachestudy.utils.CacheUtils;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        image =(ImageView)findViewById(R.id.image);

        CacheUtils cacheUtils=new CacheUtils();
        cacheUtils.getBitmap("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=49366202,632101467&fm=27&gp=0.jpg", image);
    }
}
