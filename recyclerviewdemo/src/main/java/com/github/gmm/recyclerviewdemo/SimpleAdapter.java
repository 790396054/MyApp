package com.github.gmm.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author gmm
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
    private List<String> datas;
    private Context mContext;
    private LayoutInflater mInflater;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    SimpleAdapter(Context context, List<String> datas) {
        this.datas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_main, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView.setText(datas.get(position));

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v->{
                int layoutPosition = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
            });
            holder.itemView.setOnLongClickListener(v->{
                int layoutPosition = holder.getLayoutPosition();
                return mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
            });
        }
    }

    public void add(int pos){
        datas.add("haha");
        notifyItemInserted(pos);
    }

    public void delete(int pos){
        datas.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        MyViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_text);
        }
    }

    public interface OnItemClickListener{
        /**
         * 点击事件回调方法
         * @param view
         * @param position
         */
        void onItemClick(View view, int position);

        /**
         * 长按事件回调方法
         * @param view
         * @param position
         */
        boolean onItemLongClick(View view, int position);
    }
}
