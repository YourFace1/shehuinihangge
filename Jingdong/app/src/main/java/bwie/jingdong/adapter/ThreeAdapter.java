package bwie.jingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bwie.jingdong.R;
import bwie.jingdong.bean.FenleiBean;

/**
 * Created by Maibenben on 2017/10/18.
 */

public class ThreeAdapter extends RecyclerView.Adapter<ThreeAdapter.MyThreeViewHolder>{

    private Context context;
    private List<FenleiBean.DataBean.ListBean> list;
    private ClickListener clickListener=null;

    public ThreeAdapter(Context context, List<FenleiBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyThreeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.three_level, null);
        MyThreeViewHolder threeViewHolder=new MyThreeViewHolder(view);
        //view.setOnClickListener(this);
        return threeViewHolder;
    }

    @Override
    public void onBindViewHolder(MyThreeViewHolder holder, int position) {
        holder.three_tv.setText(list.get(position).getName());
        final FenleiBean.DataBean.ListBean bean=list.get(position);
        holder.three_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener!=null){
                   clickListener.onItemClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /*//自动生成
    @Override
    public void onClick(View view) {
            clickListener.onItemClick(view, (int) view.getTag());

    }*/



    //点击事件接口
    public static interface ClickListener{
        void onItemClick(FenleiBean.DataBean.ListBean bean);
    }
    //给外面的调用者定义一个设置Listener的方法
    public void OnItemClickListener1(ClickListener listener){
        this.clickListener=listener;
    }
    public class MyThreeViewHolder extends RecyclerView.ViewHolder {

        private final TextView three_tv;

        public MyThreeViewHolder(View itemView) {
            super(itemView);
            three_tv = itemView.findViewById(R.id.three_tv);
        }
    }
}
