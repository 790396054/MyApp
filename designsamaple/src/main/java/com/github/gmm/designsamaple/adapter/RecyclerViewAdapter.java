package com.github.gmm.designsamaple.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.activity.DetailActivity;
import com.github.gmm.designsamaple.utils.AppConstants;

import java.util.List;

/**
 * @author gmm
 * @date 2018/7/7 18
 * @email miaomiaogong@92gmail.com
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> dataList;
    private Activity mActivity;

    public RecyclerViewAdapter(List<String> dataList, Activity activity) {
        this.dataList = dataList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.recyclerview_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).split(",")[0]);
        holder.content.setText(dataList.get(position).split(",")[1]);
        if (position % 2 == 0) {
            holder.showImage.setBackgroundResource(R.mipmap.show_img1);
        } else {
            holder.showImage.setBackgroundResource(R.mipmap.show_img2);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView showImage;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            showImage = itemView.findViewById(R.id.showImage);
            itemView.setOnClickListener((v)-> {
//                    DetailActivity.startActivity(mActivity, getLayoutPosition(), showImage);
                Intent intent = new Intent(mActivity, DetailActivity.class);
                intent.putExtra("position", getLayoutPosition());
//                mActivity.startActivity(intent);
                ActivityOptionsCompat options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(mActivity, showImage, AppConstants.TRANSITION_PIC);
                ActivityCompat.startActivity(mActivity, intent, options.toBundle());
            });
        }
    }
}
