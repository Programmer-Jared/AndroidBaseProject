package com.jared.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.jared.base.R;
import com.jared.base.utils.ReflectUtil;

import me.jessyan.autosize.AutoSizeCompat;
/**
 * @ClassName BaseDialog
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description todo 需要优化  适配Autosize
 */

public abstract class BaseDialog<VB extends ViewBinding> extends Dialog {
    private VB viewBinding;
    public BaseDialog(@NonNull Context context) {
        super(context, R.style.BaseDialog);
        viewBinding = ReflectUtil.getInflate(getClass(),getLayoutInflater(),null);
        setContentView(viewBinding.getRoot());
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        getWindow().setAttributes(layoutParams);
        initView();
    }

    @Override
    public void show() {
        if (this.getWindow() != null) {
            // TODO: 2022/3/3 考虑show 一些配置放到初始化中
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.height = height();
            params.width = width();
            params.gravity =gravity();
            getWindow().setAttributes(params);
            AutoSizeCompat.autoConvertDensityBaseOnWidth(getContext().getResources(),designWidthInDp());
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
//            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            super.show();
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }
    }

    protected abstract void initView();

    protected abstract int gravity();

    protected abstract int width();

    protected abstract int height();

    protected abstract int designWidthInDp();
    public interface DialogListener {
        void onConfirm(int position);
    }

    protected DialogListener listener;

    public void setDialogListener(DialogListener listener) {
        this.listener = listener;
    }
}
