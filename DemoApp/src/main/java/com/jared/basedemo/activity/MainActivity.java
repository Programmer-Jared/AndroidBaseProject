package com.jared.basedemo.activity;


import android.content.Intent;

import com.jared.base.contact.IBasePresenter;
import com.jared.base.activity.BaseActivity;
import com.jared.base.toast.BaseToast;
import com.jared.base.utils.AppManager;
import com.jared.basedemo.contact.MainActivityContact;
import com.jared.basedemo.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding, IBasePresenter>implements MainActivityContact.View {




//
//    @Override
//    protected int getContentLayoutId() {
//        return R.layout.activity_main;
//    }

//    @Override
//    public View getView() {
//        return ActivityMainBinding.inflate(getLayoutInflater()).getRoot();
//    }

    @Override
    public void initView() {

//        ActivityMainBinding.nflate(getLayoutInflater());

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {


    }

    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onSoaCarStateChange(String key, String value) {

    }
    private final static long waitTime = 2 * 1000;
    private long touchTime = 0;
    /**
     * 监听系统返回键
     */
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                BaseToast.showToast("再按一次退出");
                touchTime = currentTime;
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                AppManager.getInstance().appExit();
            }
    }
}