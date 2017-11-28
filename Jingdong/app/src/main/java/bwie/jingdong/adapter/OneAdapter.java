package bwie.jingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bwie.jingdong.R;
import bwie.jingdong.bean.FenleizhuBean;

/**
 * Created by Maibenben on 2017/10/18.
 */

public class OneAdapter extends RecyclerView.Adapter<OneAdapter.MyOneViewHolder> implements View.OnClickListener{


   private Context context;
    List<FenleizhuBean.DataBean> list;
    private OnItemClickListener sOnItemClickListener=null;
    private MyOneViewHolder oneViewHolder;

    public OneAdapter(Context context, List<FenleizhuBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.one_level, null);

        oneViewHolder = new MyOneViewHolder(view);
        view.setOnClickListener(this);
        return  oneViewHolder;
    }

    @Override
    public void onBindViewHolder(MyOneViewHolder holder, int position) {
        holder.itemView.setTag(position);


        holder.one_tv.setText(list.get(position).getName());

        if(list.get(position).getIcon().isEmpty()){
            return;
        }
        Picasso.with(holder.one_iv.getContext()).load(list.get(position).getIcon()).into(holder.one_iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自动生成
    @Override
    public void onClick(View view) {
        if(sOnItemClickListener!=null){
            //注意这里使用getTag方法获取position
            sOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    //点击事件
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    //给外面的调用者，定义一个设置Listtener的方法
    public void ssetOnItemClickListener(OnItemClickListener listener){
        this.sOnItemClickListener=listener;
    }
    public class MyOneViewHolder extends RecyclerView.ViewHolder {

        private final TextView one_tv;
        private final ImageView one_iv;

        public MyOneViewHolder(View itemView) {
            super(itemView);
            one_tv = itemView.findViewById(R.id.one_tv);
            one_iv = itemView.findViewById(R.id.one_iv);
        }
    }
}
