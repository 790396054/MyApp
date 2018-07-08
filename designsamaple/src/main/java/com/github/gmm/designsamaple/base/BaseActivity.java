package com.github.gmm.designsamaple.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.github.gmm.designsamaple.R;

/**
 * @author gmm
 * @date 2018/6/30 19
 * @email miaomiaogong@92gmail.com
 */
public class BaseActivity extends AppCompatActivity {
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    public Toolbar initToolbarAsHome() {
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        return toolbar;
    }

    public Toolbar initToolBar(String title) {
        Toolbar toolbar = findViewById(R.id.toolBar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                // 对没有处理的事件，交给父类来处理
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
