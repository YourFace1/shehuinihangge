package bwie.jingdong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bwie.jingdong.R;


/**
 * Created by Maibenben on 2017/10/9.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private List<Integer> imageList;
    public GridViewAdapter(Context context, List<String> list,List<Integer> imageList) {
        this.context = context;
        this.list = list;
        this.imageList=imageList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.item_gridview,null);
        ImageView iv = (ImageView) v.findViewById(R.id.grid_iv);
        TextView tv=(TextView)v.findViewById(R.id.grid_tv);
        iv.setImageResource(imageList.get(i));
        tv.setText(list.get(i));
        return v;
    }
}
