package bwie.jingdong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.app.MyApp;
import bwie.jingdong.bean.FindAddrBean;
import bwie.jingdong.bean.SetAddrBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

public class FindAddrActivity extends AppCompatActivity {

    private List<FindAddrBean.DataBean> data;
    private TextView address_item,mob_item,name_item;
    private ListView listView;
    private String uid;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_addr);

        listView = (ListView) findViewById(R.id.listview);
        TextView tianjiaaddr = (TextView) findViewById(R.id.tianjiaaddr);
        tianjiaaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindAddrActivity.this, AdressActivity.class));
            }
        });
        getData();



    }

    private void getData() {
        SharedPreferences sp = MyApp.getSharedPreferencesInstance(this);
        uid = sp.getString("uid", "");
        token = sp.getString("token", "");
        Log.i("oooo", uid);
        final Map<String,String> map=new HashMap<>();
        map.put("uid", uid);
        map.put("token", token);
        OkHttp3Utils.doPost(Api.FINDaddress, map, new GsonObjectCallback<FindAddrBean>() {

            @Override
            public void onUi(FindAddrBean findAddrBean) {
                data = findAddrBean.getData();
                String addr = data.get(0).getAddr();
                Log.i("qwe",addr);
                listView.setAdapter(new MyAdapter(data));
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int addrid = data.get(i).getAddrid();
                int status = data.get(i).getStatus();
                Log.i("binghao",addrid+"");
                Map<String,String> setMap=new HashMap<String, String>();
                setMap.put("uid",uid);
                setMap.put("addrid",addrid+"");
                setMap.put("status",status+"");
                setMap.put("token",token);
                OkHttp3Utils.doPost(Api.SETaddress, setMap, new GsonObjectCallback<SetAddrBean>() {
                    @Override
                    public void onUi(SetAddrBean setAddrBean) {
                        String code = setAddrBean.getCode();
                        if(code.equals("0")){
                            Toast.makeText(FindAddrActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(FindAddrActivity.this,DingdanActivity.class));
                        }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        //private Context context;
        private List<FindAddrBean.DataBean>  data=new ArrayList<>();
        public MyAdapter( List<FindAddrBean.DataBean> data) {
            //this.context = context;
            this.data = data;
        }

        /*public MyAdapter(Context context) {
            this.context = context;
        }*/

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = View.inflate(FindAddrActivity.this, R.layout.findaddress_item, null);
            address_item = v.findViewById(R.id.address_item);
            mob_item = v.findViewById(R.id.mob_item);
            name_item = v.findViewById(R.id.name_item);

            String addr = data.get(i).getAddr();
            long mobile = data.get(i).getMobile();
            String name = data.get(i).getName();
            address_item.setText(addr);
            mob_item.setText(mobile+"");
            name_item.setText(name);
            return v;
        }
    }
}
