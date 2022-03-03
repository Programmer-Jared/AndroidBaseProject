package com.jared.basedemo.contact;

import android.content.Context;

import com.jared.base.contact.IBasePresenter;
import com.jared.base.contact.IBaseView;


/**
 * @ClassName MainActivityContact
 * @Author Jared Chen
 * @Date 2021/8/23 11:55
 * @Version 1.0
 * @Description TODO
 */

public class MainActivityContact {
    public interface View extends IBaseView {


        void onSoaCarStateChange(String key, String value);
    }

    public interface MainActivityPresenterI extends IBasePresenter {
        void initSoa(Context context);


    }
}
