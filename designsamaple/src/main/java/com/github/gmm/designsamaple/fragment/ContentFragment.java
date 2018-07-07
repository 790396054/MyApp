package com.github.gmm.designsamaple.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.adapter.RecyclerViewAdapter;
import com.github.gmm.designsamaple.utils.KeyConstants;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gmm
 * @date 2018/7/7 18
 * @email miaomiaogong@92gmail.com
 */
public class ContentFragment extends Fragment {
    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;
    private int page = 1;

    private String mTitle;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<String> mDataList = new ArrayList<>();
    private Runnable runnable;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mTitle = getArguments().getString(KeyConstants.TITLE);
        mPullLoadMoreRecyclerView = view.findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mDataList, getActivity());
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mDataList.clear();
                setList();
            }

            @Override
            public void onLoadMore() {
                loadMore();
            }
        });
        isPrepared = true;
        lazyLoad();
    }

    private void setList() {
        runnable = () -> {
            int start = 20 * (page - 1);
            for (int i = start; i < page * 20; i++) {
                mDataList.add(mTitle + "," + getActivity().getString(R.string.test_data) + i);
            }
            mRecyclerViewAdapter.notifyDataSetChanged();
            mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);
    }

    private void loadMore() {
        page ++;
        setList();
    }

    /**
     * Android应用开发过程中，ViewPager同时加载多个fragment，以实现多tab页面快速切换, 但是fragment初始化时若加载的内容较多，
     * 就可能导致整个应用启动速度缓慢，影响用户体验。
     * 为了提高用户体验，我们会使用一些懒加载方案，实现加载延迟。
     * 这时我们会用到getUserVisibleHint()与setUserVisibleHint()这两个方法。
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    private void onInvisible() {

    }

    protected void onVisible() {
        lazyLoad();
    }

    private void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        setList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
