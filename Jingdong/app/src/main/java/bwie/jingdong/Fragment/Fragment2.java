package bwie.jingdong.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import bwie.jingdong.Api.Api;
import bwie.jingdong.R;
import bwie.jingdong.adapter.OneAdapter;
import bwie.jingdong.adapter.TwoAdapter;
import bwie.jingdong.bean.FenleiBean;
import bwie.jingdong.bean.FenleizhuBean;
import bwie.jingdong.presenter.FenleiPre;
import bwie.jingdong.presenter.FenleiPreSx;
import bwie.jingdong.utils.GsonObjectCallback;
import bwie.jingdong.utils.OkHttp3Utils;
import bwie.jingdong.view.FenleiView;
import okhttp3.Call;

import static bwie.jingdong.R.id.recycler_two;

/**
 * Created by Maibenben on 2017/10/31.
 */

public class Fragment2 extends Fragment implements FenleiView{

    private RecyclerView recyeler_one,recyeler_two;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        recyeler_one = view.findViewById(R.id.recycler_one);
        recyeler_two = view.findViewById(recycler_two);
        recyeler_one.setLayoutManager(new LinearLayoutManager(getActivity()));

        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(getActivity());
        recyeler_two.setLayoutManager(linearLayoutManager1);
        FenleiPre fenleiPre=new FenleiPreSx(this);
        fenleiPre.re();



        return view;
    }

    @Override
    public void FenleiData(FenleizhuBean fenleiBean) {

        final List<FenleizhuBean.DataBean> data = fenleiBean.getData();
        String code = fenleiBean.getCode();

        Log.i("iii",code);
        OneAdapter oneAdapter=new OneAdapter(getActivity(),data);
        recyeler_one.setAdapter(oneAdapter);

        oneAdapter.ssetOnItemClickListener(new OneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int cid = data.get(position).getCid();
                OkHttp3Utils.doGet(Api.fenlei2+cid, new GsonObjectCallback<FenleiBean>() {
                    @Override
                    public void onUi(FenleiBean twoBean) {
                        List<FenleiBean.DataBean> data1 = twoBean.getData();
                        TwoAdapter twoAdapter=new TwoAdapter(getActivity(),data1);
                        recyeler_two.setAdapter(twoAdapter);
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });
    }
}
