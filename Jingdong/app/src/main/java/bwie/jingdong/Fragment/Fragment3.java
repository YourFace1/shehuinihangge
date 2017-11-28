package bwie.jingdong.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.activity.DingdanActivity;
import bwie.jingdong.bean.DeleteBean;
import bwie.jingdong.bean.FindCarBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;

import static bwie.jingdong.R.layout.group;

/**
 * Created by Maibenben on 2017/10/31.
 */

public class Fragment3 extends Fragment implements View.OnClickListener{

    private List<FindCarBean.DataBean> data;

    private ExpandableListView ex;
    private CheckBox cb_all;
    private TextView shop_allmoney;
    private TextView shop_count;
    private SharedPreferences sp;
    private Exadapter exadapter;
    private Map<String, String> map;
    private String token;
    private String uid;
    private FindCarBean.DataBean.ListBean listBean;
    private double price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);

        initView(view);
       // getData();
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        token = sp.getString("token","");
        uid = sp.getString("uid", "");
        map = new HashMap<>();
        map.put("token", token);
        map.put("uid", uid);

        OkHttp3Utils.doPost(Api.FINDSHOPCAR, map, new GsonObjectCallback<FindCarBean>() {


            @Override
            public void onUi(FindCarBean findCarBean) {
                Log.i("TAG","aaaaa");
                data = findCarBean.getData();
                Log.i("TAG",data.size()+"bbbbaa");
                exadapter = new Exadapter(getActivity());
                ex.setAdapter(exadapter);

                for (int i = 0; i < data.size(); i++) {
                    ex.expandGroup(i);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
//父类点击事件
       /* ex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean clickable = ex.isClickable();

                data.get(i).setSetAllbool(clickable);

                if(clickable){
                    all(clickable);
                    //判断是否全选
                    if(select()){

                    }
                }
            }


        });
*/

        return view;
    }

    //点击父类改变子类
    private void all(boolean b) {
        for(int i=0;i<data.size();i++){
            List<FindCarBean.DataBean.ListBean> listBeen = this.data.get(i).getList();
            if(data.get(i).getSetAllbool()==b){
                for(int j=0;j<listBeen.size();j++){
                    listBeen.get(j).setSetZibool(b);
                }
            }
        }
    }

    //判断一级是否全选中
    public boolean select(){
        for(int i=0;i<data.size();i++){
            if(!data.get(i).getSetAllbool()){
                return false;
            }
        }
        return true;
    }

    private void initView(View view) {
        ex = (ExpandableListView) view.findViewById(R.id.ex);
        cb_all = (CheckBox) view.findViewById(R.id.cb_all);
        shop_allmoney = (TextView) view.findViewById(R.id.shop_allmoney);
        shop_count = (TextView) view.findViewById(R.id.shop_count);
        cb_all.setOnClickListener(this);

        shop_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), DingdanActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("token",token);
                //double price = listBean.getPrice();
                String s = price+"";
                Log.i("jiage",s);
                intent.putExtra("price",s);

                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(((CheckBox)view).isChecked()){
            List<FindCarBean.DataBean> cart_list = this.data;
            for (int i = 0; i < cart_list.size(); i++) {
                FindCarBean.DataBean cartListBean = cart_list.get(i);
                cartListBean.setSetAllbool(true);
                List<FindCarBean.DataBean.ListBean> goods = cart_list.get(i).getList();
                for (int j = 0; j < goods.size(); j++) {
                    List<FindCarBean.DataBean.ListBean> beanList = cartListBean.getList();
                    for (FindCarBean.DataBean.ListBean childData : beanList) {
                        childData.setSetZibool(true);
                    }
                }
            }
            //刷新界面
            notifyCheckAdapter();
        }else {
            List<FindCarBean.DataBean> cart_list = this.data;
            for (int i = 0; i < cart_list.size(); i++) {
                FindCarBean.DataBean cartListBean = cart_list.get(i);
                cartListBean.setSetAllbool(false);
                List<FindCarBean.DataBean.ListBean> goods = cart_list.get(i).getList();
                for (int j = 0; j < goods.size(); j++) {
                    List<FindCarBean.DataBean.ListBean> beanList = cartListBean.getList();
                    for (FindCarBean.DataBean.ListBean childData : beanList) {
                        childData.setSetZibool(false);
                    }
                }
            }
            //刷新界面
            notifyCheckAdapter();
        }
    }


    class Exadapter extends BaseExpandableListAdapter {
        private Context context;
        private View view1;

        //删除子条目
        public void deleteData(int groupPosition,int childPosition){
            data.get(groupPosition).getList().remove(childPosition);
            notifyDataSetChanged();
        }

        //删除组条目
        public void deleteGroup(int groupPosition){
            data.remove(groupPosition);
            notifyDataSetChanged();
        }

        public Exadapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            //Log.i("TAG",data.size()+"aaaaa");
            return data.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return data.get(i).getList().size();
        }

        @Override
        public Object getGroup(int i) {
            return data.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return data.get(i).getList().get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            if(view==null){
                view1 = LayoutInflater.from(context).inflate(group, null);
            }
            CheckBox group_check = view1.findViewById(R.id.group_check);
            TextView group_name = view1.findViewById(R.id.group_name);
            group_name.setText(data.get(i).getSellerName());


            //FindCarBean.DataBean cartListBean = data.get(i);
            //groupViewHolder.group_store_name.setText(cartListBean.getStore_name());
            //一级
            if(data.get(i).getSetAllbool()){
                //被选中则设置为选中状态
                group_check.setChecked(true);
            }else{ //否则设置为未选中
                group_check.setChecked(false);
            }

            //一级监听   将下标和组件传入
            group_check.setOnClickListener(new onGroupClickListener(i,group_check));
            return view1;
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
            if (view == null){
                view1 = LayoutInflater.from(context).inflate(R.layout.zi,null);
            }
            ImageView child_img = view1.findViewById(R.id.child_img);
            TextView chidl_price = view1.findViewById(R.id.chidl_price);
            TextView child_title = view1.findViewById(R.id.chidl_title);
            CheckBox child_check = view1.findViewById(R.id.child_check);
            Button child_delete = view1.findViewById(R.id.child_delete);

            chidl_price.setText(data.get(i).getList().get(i1).getPrice()+"");
            String images = data.get(i).getList().get(i1).getImages();
            String[] split = images.split("!");
            String s = split[0];
            Picasso.with(context).load(s).placeholder(R.mipmap.ic_launcher).into(child_img);
            child_title.setText(data.get(i).getList().get(i1).getTitle());

            listBean = data.get(i).getList().get(i1);
            child_check.setChecked(data.get(i).getList().get(i1).getSetZibool());
            child_check.setOnClickListener(new onChildCheckListener(i,i1,child_check));


            //删除子条目
            child_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    map.put("pid", listBean.getPid()+"");
                    OkHttp3Utils.doPost( Api.DELSHOPCAR,map, new GsonObjectCallback<DeleteBean>() {
                        @Override
                        public void onUi(DeleteBean unregBean) {
                            String code = unregBean.getCode();
                            if(code.equals("0")){
                                Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
                                deleteData(i,i1);
                                notifyCheckAdapter();
                                if(data.get(i).getList().size() == 0){
                                    deleteGroup(i1);
                                }
                            }
                        }

                        @Override
                        public void onFailed(Call call, IOException e) {

                        }
                    });
                }
            });
            return view1;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    /**
     * 设置一级监听的类
     */
    public class onGroupClickListener implements View.OnClickListener{

        int groupPosition;
        CheckBox group_cb;

        public onGroupClickListener(int groupPosition, CheckBox group_cb) {
            this.groupPosition = groupPosition;
            this.group_cb = group_cb;
        }

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()){
                //一级全选
                setCheck(true);
            }else{
                setCheck(false);
                cb_all.setChecked(false);
            }
            notifyCheckAdapter();

        }

        //设置选中
        public void setCheck(boolean checkFlag){
            //获取集合中每一条数据
            FindCarBean.DataBean cartListBean = data.get(groupPosition);
            //一级状态
            cartListBean.setSetAllbool(checkFlag);
            //全选状态判断
            int num = 0;
            for (int i = 0;i<data.size();i++){
                boolean allCheck = data.get(i).getSetAllbool();
                if(!allCheck){
                    num ++;
                }
            }
            if(num == 0){
                cb_all.setChecked(true);
            }else{
                cb_all.setChecked(false);
            }

            //二级状态
            List<FindCarBean.DataBean.ListBean> goods = data.get(groupPosition).getList();
            for(FindCarBean.DataBean.ListBean goodsbean:goods){
                goodsbean.setSetZibool(checkFlag);
            }
        }

    }
    /**
     * 二级监听
     */
    public class onChildCheckListener implements View.OnClickListener{
        int groupPosition;
        int childPosition;
        CheckBox cb_child;

        public onChildCheckListener(int groupPosition, int childPosition, CheckBox cb_child) {
            this.cb_child = cb_child;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()){
                //子选中
                data.get(groupPosition).getList().get(childPosition).setSetZibool(true);
            }else{
                //子未选中
                data.get(groupPosition).getList().get(childPosition).setSetZibool(false);
            }

            //二级联动一级状态
            setParentCheckFlag();

            //检测状态  二级全联选中
            int num = 0;
            for(int i = 0;i<data.size();i++){
                boolean allCheck = data.get(i).getSetAllbool();
                if (!allCheck) {
                    num++;
                }
            }
            if (num == 0) {
                cb_all.setChecked(true);
            } else {
                cb_all.setChecked(false);
            }
        }

        //二级联动一级状态
        private void setParentCheckFlag(){
            FindCarBean.DataBean cartListBean = data.get(groupPosition);
            List<FindCarBean.DataBean.ListBean> goods = cartListBean.getList();
            for (int i = 0; i < goods.size(); i++) {
                if (!goods.get(i).getSetZibool()) {
                    //子未选中 父取消选中
                    cartListBean.setSetAllbool(false);
                    notifyCheckAdapter();
                    return;
                }
                if (i == goods.size() - 1) {
                    //子选中 父选中
                    cartListBean.setSetAllbool(true);
                    notifyCheckAdapter();
                    return;
                }
            }
            // 没出现全选或者取消全选的时候执行的
            sum();
        }

    }
    //统计数量和价格
    private void sum() {
        int num = 0;
        price = 0;
        List<FindCarBean.DataBean> cart_list = this.data;
        for (FindCarBean.DataBean parentData : cart_list) {
            for (FindCarBean.DataBean.ListBean child : parentData.getList()) {
                if (child.getSetZibool()) {
                    num++;
                    double i = child.getPrice();
                    price += i;
                }
            }
        }
        shop_count.setText("结算(" + num + ")");
        shop_allmoney.setText("¥" + price);
    }
    //刷新适配器界面
    private void notifyCheckAdapter() {
        sum();
        ex.setAdapter(exadapter);
        int count = ex.getCount();
        for (int i = 0; i < count; i++) {
            ex.expandGroup(i);
        }
    }
}
