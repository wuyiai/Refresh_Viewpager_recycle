package com.haishan.refresh_viewpager_recycle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {

    private RecyclerView recyccleview;
    public MoneyListAdapter mAdapter;

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
        mAdapter = new MoneyListAdapter(getActivity());
        recyccleview.setAdapter(mAdapter);
    }

    public void requestData(){

        Log.v("jiazaizhong", "requestData111111111111111");

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
