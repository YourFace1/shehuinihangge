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
import bwie.jingdong.bean.ShouyeBean;

/**
 * Created by Maibenben on 2017/10/8.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;
    private ShouyeBean shouyeBean;
    private List<ShouyeBean.TuijianBean.ListBean> list;

    public RecycleAdapter(Context context, ShouyeBean shouyeBean) {
        this.context = context;
        this.shouyeBean=shouyeBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pubu, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.pubu_iv.getLayoutParams();
        if(position==0){
            layoutParams.height=200;
        }else {
            layoutParams.height=350;
        }
        holder.pubu_iv.setLayoutParams(layoutParams);
        list = shouyeBean.getTuijian().getList();


            String images = list.get(position).getImages();
            String[] split = images.split("\\|");
            String image = split[0];
            Picasso.with(holder.pubu_iv.getContext()).load(image).into(holder.pubu_iv);

        holder.pubu_tv.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return shouyeBean.getData().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView pubu_tv;
        ImageView pubu_iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            pubu_tv = itemView.findViewById(R.id.pubu_tv);
            pubu_iv = itemView.findViewById(R.id.pubu_iv);
        }
    }

}

