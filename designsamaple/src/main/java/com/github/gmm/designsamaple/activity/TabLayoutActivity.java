package com.github.gmm.designsamaple.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gmm.designsamaple.R;

/**
 * @author gmm
 * @date 2018-06-30
 */
public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));

        View view = View.inflate(this, R.layout.custom_tab_1, null);
        TextView textView = view.findViewById(R.id.tabText);
        textView.setText("Tab4");
        TextView point = view.findViewById(R.id.tabPoint);
        point.setText("9");
        tabLayout.addTab(tabLayout.newTab().setCustomView(view));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(TabLayoutActivity.this, "onTabSelected"+tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Toast.makeText(TabLayoutActivity.this, "onTabUnselected"+tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(TabLayoutActivity.this, "onTabReselected"+tab.getPosition(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
