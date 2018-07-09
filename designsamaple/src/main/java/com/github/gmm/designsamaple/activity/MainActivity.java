package com.github.gmm.designsamaple.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.gmm.designsamaple.R;
import com.github.gmm.designsamaple.base.BaseActivity;
import com.github.gmm.designsamaple.fragment.FirstFragment;
import com.github.gmm.designsamaple.fragment.SecondFragment;
import com.github.gmm.designsamaple.fragment.ThirdFragment;
import com.github.gmm.designsamaple.utils.AppConstants;
import com.wuxiaolong.androidutils.library.LogUtil;
import com.wuxiaolong.androidutils.library.SharedPreferencesUtil;

/**
 * @author gmm
 * @date 2018/6/30 19
 * @email miaomiaogong@92gmail.com
 */
public class MainActivity extends BaseActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private Fragment currentFragment;
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        initNavigationViewHeader();
        initFragment(savedInstanceState);
    }

    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            mDrawerToggle.syncState();
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                // 更换
                actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
            }
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            currentFragment = new FirstFragment();
            switchFragment(currentFragment);
        } else {
            // activity 销毁后记住销毁前所在页面，用于切换夜间模式
            currentIndex = savedInstanceState.getInt(AppConstants.CURRENT_INDEX);
            switch (this.currentIndex) {
                case 0:
                    currentFragment = new FirstFragment();
                    break;
                case 1:
                    currentFragment = new SecondFragment();
                    break;
                case 2:
                    currentFragment = new ThirdFragment();
                    break;
                default:
                    break;
            }
            switchFragment(currentFragment);
        }
    }

    public void switchFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentLayout, fragment).commit();
        invalidateOptionsMenu();
    }

    private void initNavigationViewHeader() {
        mNavigationView = findViewById(R.id.navigation);
        // 设置头像，布局 app:headerLayout="@layout/drawer_header" 所指定的头布局
        View view = mNavigationView.inflateHeaderView(R.layout.drawer_header);
        TextView userName = view.findViewById(R.id.userName);
        userName.setText(R.string.author);

        //View mNavigationViewHeader = View.inflate(MainActivity.this, R.layout.drawer_header, null);
        //navigationView.addHeaderView(mNavigationViewHeader);//此方法在魅族note 1，头像显示不全
        // 菜单点击事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationItemSelected());
    }

    class NavigationItemSelected implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mDrawerLayout.closeDrawers();
            switch (item.getItemId()) {
                case R.id.navigation_item1:
                    currentIndex = 0;
                    item.setChecked(true);
                    currentFragment = new FirstFragment();
                    switchFragment(currentFragment);
                    return true;
                case R.id.navigation_item2:
                    currentIndex = 2;
                    item.setChecked(true);
                    currentFragment = new SecondFragment();
                    switchFragment(currentFragment);
                    return true;
                case R.id.navigation_item3:
                    currentIndex = 1;
                    item.setChecked(true);
                    currentFragment = new ThirdFragment();
                    switchFragment(currentFragment);
                    return true;
                case R.id.navigation_item_night:
                    SharedPreferencesUtil.setBoolean(mActivity, AppConstants.ISNIGHT, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                    return true;
                case R.id.navigation_item_day:
                    SharedPreferencesUtil.setBoolean(mActivity, AppConstants.ISNIGHT, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                    return true;
                default:
                    return true;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        LogUtil.d("onSaveInstanceState=" + currentIndex);
        outState.putInt(AppConstants.CURRENT_INDEX, currentIndex);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.about:
                break;
            default:
                //对没有处理的事件，交给父类来处理
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}