package com.haishan.refresh_viewpager_recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MoneyListAdapter extends RecyclerView.Adapter<MoneyListAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    public List<String> mDateBeen = new ArrayList<>();
    private MoneyListAdapter.OnItemClickListener mItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_type;

        public ViewHolder(View view) {
            super(view);
            tv_type = (TextView) view.findViewById(R.id.tv);
        }
    }

    public void setDataBean(List<String> data) {
        this.mDateBeen = data;
        notifyDataSetChanged();
    }

    public List<String> getData(){
        return mDateBeen;
    }

    public void addData(List<String> data) {
        this.mDateBeen.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData(){
        mDateBeen.clear();
        notifyDataSetChanged();
    }

    public MoneyListAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public MoneyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        RecyclerView.ViewHolder holder = new MoneyListAdapter.ViewHolder(view);
        view.setOnClickListener(this);
        return new MoneyListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MoneyListAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        final String bean = mDateBeen.get(position);
        holder.tv_type.setText(""+bean);
    }

    @Override
    public int getItemCount() {
        if (mDateBeen != null && mDateBeen.size() > 0) {
            return mDateBeen.size();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(MoneyListAdapter.OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

}
