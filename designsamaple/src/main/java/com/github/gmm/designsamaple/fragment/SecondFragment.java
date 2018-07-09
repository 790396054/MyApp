package com.github.gmm.designsamaple.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.activity.BottomNavigationActivity;
import com.github.gmm.designsamaple.activity.MainActivity;
import com.github.gmm.designsamaple.activity.TabLayoutActivity;
import com.github.gmm.designsamaple.base.BaseFragment;

/**
 *
 * @author gmm
 * @date 2018/6/30 20
 * @email miaomiaogong@92gmail.com
 */
public class SecondFragment extends BaseFragment {
    private Toolbar mToolbar;
    private View thirdLayout;
    private SwitchCompat switchCompat;
    private CheckBox checkBox;
    private RadioButton radiobutton;
    private SeekBar seekBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return LayoutInflater.from(mActivity).inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle("其他组件");

        ((MainActivity) getActivity()).initDrawer(mToolbar);

        thirdLayout = view.findViewById(R.id.thirdLayout);
        switchCompat = view.findViewById(R.id.switchCompat);
        checkBox = view.findViewById(R.id.checkbox);
        radiobutton = view.findViewById(R.id.radiobutton);
        seekBar = view.findViewById(R.id.seekBar);

        view.findViewById(R.id.tabLayout).setOnClickListener(v -> startActivity(new Intent(mActivity, TabLayoutActivity.class)));
        view.findViewById(R.id.bottomNavigation).setOnClickListener(v->new Intent(mActivity, BottomNavigationActivity.class));
        view.findViewById(R.id.snackbars).setOnClickListener(v -> Snackbar.make(thirdLayout, "Snackbar Test", Snackbar.LENGTH_SHORT).show());
        view.findViewById(R.id.dialogs).setOnClickListener(v-> showDialogs());
    }

    private void showDialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("OK", (dialog, which)->{
           showToast("KO");
        });
        builder.setNegativeButton("Cancel", (dialog, which)->{
           showToast("Cancel");
        });
        builder.show();
    }
}
