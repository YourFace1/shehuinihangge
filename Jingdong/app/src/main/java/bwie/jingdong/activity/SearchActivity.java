package bwie.jingdong.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import bwie.jingdong.R;
import bwie.jingdong.adapter.GuanAdapter;
import bwie.jingdong.bean.GjzBean;
import bwie.jingdong.presenter.GjzPre;
import bwie.jingdong.presenter.GjzPreSx;
import bwie.jingdong.view.GjzView;

public class SearchActivity extends AppCompatActivity implements GjzView{

    private EditText gunjianzi;
    private GjzPre gjzPre;
    private RecyclerView guanRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        guanRlv = (RecyclerView) findViewById(R.id.GuanRlv);
        guanRlv.setLayoutManager(new LinearLayoutManager(this));
        gunjianzi = (EditText) findViewById(R.id.gunjianzi);
        TextView find = (TextView) findViewById(R.id.find);
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.this.finish();
            }
        });
        gjzPre = new GjzPreSx(this);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = gunjianzi.getText().toString();

                //p关联v

                gjzPre.setType(s);
            }
        });
    }

    @Override
    public void GjzData(GjzBean gjzBean) {
        GuanAdapter adapter=new GuanAdapter(this,gjzBean);
        guanRlv.setAdapter(adapter);
    }
}
