package com.github.gmm.designsamaple.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.activity.LoginActivity;
import com.github.gmm.designsamaple.activity.MainActivity;
import com.github.gmm.designsamaple.base.BaseFragment;
import com.github.gmm.designsamaple.utils.KeyConstants;
import com.wuxiaolong.androidutils.library.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author gmm
 * @date 2018/6/30 20
 * @email miaomiaogong@92gmail.com
 */
public class FirstFragment extends BaseFragment {
    private Toolbar mToolbar;
    private FloatingActionButton mFab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mFab = view.findViewById(R.id.fab);
        mToolbar = view.findViewById(R.id.toolBar);
        mToolbar.setTitle("首页");
        //设置标题居中
        ((MainActivity) Objects.requireNonNull(getActivity())).initDrawer(mToolbar);
        inflateMenu();
        initTabLayout(view);
        initSearchView();
        mFab.setOnClickListener((v)->{
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });
    }

    private void initSearchView() {
        final SearchView searchView = (SearchView) mToolbar.getMenu().findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint("搜索");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showToast("query=" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                LogUtil.d("onQueryTextChange=" + newText);
                showToast("newText=" + newText);
                return false;
            }
        });
    }

    private void initTabLayout(View view) {
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        setupWithPager(viewPager);
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        // TabLayout 和 ViewPager 关联
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void setupWithPager(ViewPager viewPager) {
    // 需要管理相互独立的并且隶属于Activity的Fragment使用FragmentManager（），
    // 而在Fragment中动态的添加Fragment要使用getChildFragmetManager（）来管理
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getChildFragmentManager());

        Fragment newfragment = new ContentFragment();
        Bundle data = new Bundle();
        data.putInt("id", 0);
        data.putString("title", getString(R.string.page1));
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, getString(R.string.page1));

        newfragment = new ContentFragment();
        data = new Bundle();
        data.putInt("id", 1);
        data.putString("title", getString(R.string.page2));
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, getString(R.string.page2));

        newfragment = new ContentFragment();
        data = new Bundle();
        data.putInt("id", 2);
        data.putString(KeyConstants.TITLE, getString(R.string.page3));
        newfragment.setArguments(data);
        adapter.addFrag(newfragment, getString(R.string.page3));

        viewPager.setAdapter(adapter);
    }

    private void inflateMenu() {
        mToolbar.inflateMenu(R.menu.menu_first);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_about:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mActivity.getString(R.string.blog)));
                        mActivity.startActivity(intent);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    private class FragmentViewPagerAdapter extends FragmentPagerAdapter{
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mTitleList = new ArrayList<>();

        public FragmentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mTitleList.add(title);
        }
    }
}
