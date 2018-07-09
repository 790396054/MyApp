package com.github.gmm.designsamaple.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.base.BaseActivity;
import com.github.gmm.designsamaple.utils.AppConstants;

/**
 * @author gmm
 * @date 2018/7/8 20
 * @email miaomiaogong@92gmail.com
 */
public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("详情标题");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v->{
            Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", v1 -> {
                        Toast.makeText(mActivity, "haha", Toast.LENGTH_SHORT).show();
                    }).show();
        });

        int position = this.getIntent().getIntExtra("position", 0);
        ImageView imageView = findViewById(R.id.backdrop);
        // 设置过度动画
        ViewCompat.setTransitionName(imageView, AppConstants.TRANSITION_PIC);
        if (position % 2 == 0) {
            imageView.setImageResource(R.mipmap.show_img1);
//            imageView.setBackgroundResource(R.mipmap.show_img1);
        } else {
            imageView.setBackgroundResource(R.mipmap.show_img2);
        }
    }

    /**
     * @param showImage 共享的元素
     */
    public static void startActivity(Activity activity, int position, ImageView showImage) {
        Intent intent = new Intent();
        intent.setClass(activity, DetailActivity.class);
        intent.putExtra("position", position);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, showImage, AppConstants.TRANSITION_PIC);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
