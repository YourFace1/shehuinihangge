package bwie.jingdong.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import bwie.jingdong.R;
import bwie.jingdong.activity.Myimageloader;
import bwie.jingdong.activity.SearchActivity;
import bwie.jingdong.adapter.GridViewAdapter;
import bwie.jingdong.adapter.RecycleAdapter;
import bwie.jingdong.bean.ShouyeBean;
import bwie.jingdong.presenter.ShouyePSx;
import bwie.jingdong.view.ShouyeView;

/**
 * Created by Maibenben on 2017/10/31.
 */

public class Fragment1 extends Fragment implements ShouyeView{
    private List<String > list=new ArrayList<String >();
    private List<Integer> gridviewList=new ArrayList<>();
    private List<String> nameList=new ArrayList<>();
    private RecyclerView shouye_rec;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //轮播的图片的链接
        list.add("https://img11.360buyimg.com/da/jfs/t9595/285/2471111611/183642/3aad4810/59f7e3afN583ea737.jpg");
        list.add("https://img1.360buyimg.com/da/jfs/t10234/221/2275861331/98746/1fdde6b1/59f2a6c7N7eb65012.jpg");
        list.add("https://img1.360buyimg.com/da/jfs/t9724/246/2446332828/98598/c39a8515/59f7ffd9N68a14d35.jpg");
        list.add("https://img12.360buyimg.com/babel/jfs/t9841/254/2549490870/161456/f3a0c360/59f9748bN22cd6dc4.jpg");
        list.add("https://img1.360buyimg.com/da/jfs/t11257/332/807095808/80269/4c43a43c/59f99451Na394db45.jpg");
        //banner轮播
        Banner();
        //GridView显示信息
        gvData();

        shouye_rec = getView().findViewById(R.id.shouye_rec);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        shouye_rec.setLayoutManager(staggeredGridLayoutManager);
        //p关联V
        ShouyePSx shouyePSx = new ShouyePSx(this);
        shouyePSx.setData();

        EditText search = getActivity().findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
    }

    private void gvData() {
        GridView grid = (GridView) getView().findViewById(R.id.grid);
        gridviewList.add(R.drawable.a);
        gridviewList.add(R.drawable.b);
        gridviewList.add(R.drawable.c);
        gridviewList.add(R.drawable.d);
        gridviewList.add(R.drawable.e);
        gridviewList.add(R.drawable.f);
        gridviewList.add(R.drawable.g);
        gridviewList.add(R.drawable.h);
        gridviewList.add(R.drawable.i);
        gridviewList.add(R.drawable.j);

        nameList.add("京东超市");
        nameList.add("全球购");
        nameList.add("京东服饰");
        nameList.add("京东生鲜");
        nameList.add("京东到家");
        nameList.add("充值缴费");
        nameList.add("领京豆");
        nameList.add("领券");
        nameList.add("赚钱");
        nameList.add("PLUS会员");

        grid.setAdapter(new GridViewAdapter(getActivity(),nameList,gridviewList));

    }

    private void Banner() {
        //轮播
        Banner banner = getView().findViewById(R.id.banner);
        banner.setImageLoader(new Myimageloader());
        //自己建一个list集合放图片
        banner.setImages(list);
        //几秒切换一次图片
        banner.setDelayTime(2000);
        banner.start();
    }

    @Override
    public void ShouyeData(ShouyeBean shouyeBean) {
        RecycleAdapter adapter=new RecycleAdapter(getActivity(),shouyeBean);
        shouye_rec.setAdapter(adapter);
    }
}
