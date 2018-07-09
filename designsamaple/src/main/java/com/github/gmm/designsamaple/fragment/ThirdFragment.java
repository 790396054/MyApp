package com.github.gmm.designsamaple.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.activity.MainActivity;
import com.github.gmm.designsamaple.base.BaseFragment;

/**
 *
 * @author gmm
 * @date 2018/6/30 20
 * @email miaomiaogong@92gmail.com
 */
public class ThirdFragment extends BaseFragment {

    private Toolbar mToolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return LayoutInflater.from(mActivity).inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle("弓苗苗");
        ((MainActivity) getActivity()).initDrawer(mToolbar);
        view.findViewById(R.id.weixin).setOnClickListener(v-> toWebView("http://weixin.sogou.com/weixin?type=1&query=吴小龙同学"));
        view.findViewById(R.id.blog).setOnClickListener(v -> toWebView("http://wuxiaolong.me/"));
        view.findViewById(R.id.github).setOnClickListener(v -> toWebView("https://github.com/WuXiaolong/"));
    }

    private void toWebView(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
