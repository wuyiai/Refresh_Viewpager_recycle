package com.haishan.refresh_viewpager_recycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment {

    private RecyclerView recyccleview;
    public MoneyList1Adapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, null);
        recyccleview = view.findViewById(R.id.recyccleview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyccleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyccleview.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MoneyList1Adapter(getActivity());
        recyccleview.setAdapter(mAdapter);
    }

    public void requestData(){

        Log.v("jiazaizhong", "requestData2222222222222222");

        List<String> list = new ArrayList<>();

        for(int i = 1;i<8;i++){
            list.add(new String(""+i));
        }

        if(mAdapter.mDateBeen.size()>13){
            return;
        }

        mAdapter.addData(list);
    }

}
