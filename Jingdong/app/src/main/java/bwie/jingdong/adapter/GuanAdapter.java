package bwie.jingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bwie.jingdong.R;
import bwie.jingdong.bean.GjzBean;

/**
 * Created by Maibenben on 2017/10/8.
 */

public class GuanAdapter extends RecyclerView.Adapter<GuanAdapter.MyViewHolder> {

    private Context context;
    private GjzBean gjzBean;
    private List<GjzBean.DataBean> list;

    public GuanAdapter(Context context, GjzBean gjzBean) {
        this.context = context;
        this.gjzBean=gjzBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guanjianci_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*ViewGroup.LayoutParams layoutParams = holder.pubu_iv.getLayoutParams();
        if(position==0){
            layoutParams.height=200;
        }else {
            layoutParams.height=350;
        }
        holder.pubu_iv.setLayoutParams(layoutParams);*/
        list = gjzBean.getData();


            String images = list.get(position).getImages();
            String[] split = images.split("!");
            String image = split[0];
            Picasso.with(holder.guan_iv.getContext()).load(image).into(holder.guan_iv);

        holder.guan_tv.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return gjzBean.getData().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView guan_tv;
        ImageView guan_iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            guan_tv = itemView.findViewById(R.id.guan_tv);
            guan_iv = itemView.findViewById(R.id.guan_iv);
        }
    }

}

