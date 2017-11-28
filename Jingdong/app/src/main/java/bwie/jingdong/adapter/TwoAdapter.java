package bwie.jingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.activity.ZIFenleiActivity;
import bwie.jingdong.bean.FenleiBean;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import okhttp3.Call;


/**
 * Created by Maibenben on 2017/10/18.
 */

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.MytwoViewHolder> implements View.OnClickListener{

    private ThreeAdapter threeAdapter;

   private Context context;
    List<FenleiBean.DataBean> twolist;
    private OnItemClickListener setOnItemClickListener;
    private MytwoViewHolder twoViewHolder;

    List<FenleiBean.DataBean.ListBean> list;
    public TwoAdapter(Context context, List<FenleiBean.DataBean> twolist) {
        this.context = context;
        this.twolist = twolist;
    }

    @Override
    public MytwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.two_level, null);
        twoViewHolder = new MytwoViewHolder(view);
        return twoViewHolder;
    }

    @Override
    public void onBindViewHolder(final MytwoViewHolder holder, final int position) {
        holder.itemView.setTag(position);


        holder.two_tv.setText(twolist.get(position).getName());

        final int i = Integer.parseInt(twolist.get(position).getCid());
        OkHttp3Utils.doGet(Api.fenlei2+i, new GsonObjectCallback<FenleiBean>() {



            @Override
            public void onUi(FenleiBean twoBean) {
                list = twoBean.getData().get(position).getList();
                GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
                holder.recyeler_three.setLayoutManager(gridLayoutManager);

                threeAdapter = new ThreeAdapter(context,list);
                holder.recyeler_three.setAdapter(threeAdapter);

                threeAdapter.OnItemClickListener1(new ThreeAdapter.ClickListener() {
                    @Override
                    public void onItemClick(FenleiBean.DataBean.ListBean bean) {
                        String pscid=bean.getPscid()+"";
                        Toast.makeText(context,pscid, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, ZIFenleiActivity.class);
                        intent.putExtra("pscid",pscid);
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


        /*if(list.get(position).getIcon().isEmpty()){
            return;
        }
        Picasso.with(holder.one_iv.getContext()).load(list.get(position).getImage()).into(holder.one_iv);*/
    }

    @Override
    public int getItemCount() {
        return twolist.size();
    }

    //自动生成
    @Override
    public void onClick(View view) {
        if(setOnItemClickListener!=null){
            //注意这里使用getTag方法获取position
            setOnItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }

    //点击事件
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    //给外面的调用者，定义一个设置Listtener的方法
    public void setOnItemClickListener(OnItemClickListener listener){
        this.setOnItemClickListener=listener;
    }
    public class MytwoViewHolder extends RecyclerView.ViewHolder {

        private final TextView two_tv;
        private final RecyclerView recyeler_three;

        public MytwoViewHolder(View itemView) {
            super(itemView);
            two_tv = itemView.findViewById(R.id.two_tv);
            recyeler_three = itemView.findViewById(R.id.recyeler_three);
        }
    }
}
