package bwie.jingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bwie.jingdong.R;
import bwie.jingdong.bean.FenleisanBean;

/**
 * Created by Maibenben on 2017/11/10.
 */

public class XqAdapter extends RecyclerView.Adapter<XqAdapter.MyViewHolder> {

    private Context context;
    private List<FenleisanBean.DataBean> list;
    private OnItemClickListener setOnItemClickListener;
    //点击事件
    public static interface OnItemClickListener{
        void onItemClick(int id);
    }
    //给外面的调用者，定义一个设置Listtener的方法
    public void setOnItemClickListener(OnItemClickListener listener){
        this.setOnItemClickListener=listener;
    }
    public XqAdapter(Context context, List<FenleisanBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.xq_item, null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setOnItemClickListener!=null){
                   setOnItemClickListener.onItemClick(list.get(position).getPid());
                }
            }
        });



        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        String image = split[0];
        Picasso.with(context).load(image).into(holder.xq_iv);

        holder.xq_tv.setText(list.get(position).getTitle());

        holder.xq_tv2.setText("¥"+String.valueOf(list.get(position).getPrice()));
        Log.i("price",String.valueOf(list.get(position).getPrice()));
    }

    //自动生成
    /*@Override
    public void onClick(View view) {
        if(setOnItemClickListener!=null){
            //注意这里使用getTag方法获取position
            setOnItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }*/


    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView xq_iv;
        TextView xq_tv,xq_tv2;
        private final LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            xq_iv = itemView.findViewById(R.id.xq_iv);
            xq_tv = itemView.findViewById(R.id.xq_tv);
            xq_tv2 = itemView.findViewById(R.id.xq_tv2);
            linearLayout = itemView.findViewById(R.id.ll);
        }
    }


}
