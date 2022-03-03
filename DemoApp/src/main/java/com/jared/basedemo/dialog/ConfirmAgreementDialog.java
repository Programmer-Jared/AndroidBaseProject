//package com.jared.basedemo.dialog;
//
//import static com.reachauto.xg9phoneproject.Constant.designWidthInDp;
//
//import android.content.Context;
//import android.text.Spannable;
//import android.text.SpannableStringBuilder;
//import android.text.style.ForegroundColorSpan;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//
//import com.jared.base.mvpbase.baseImpl.BaseDialog;
//import com.jared.base.utils.DensityUtils;
//import com.reachauto.xg9phoneproject.R;
//import com.reachauto.xg9phoneproject.databinding.DialogAgreementBinding;
//
//public class ConfirmAgreementDialog extends BaseDialog {
//    public ConfirmAgreementDialog(@NonNull Context context) {
//        super(context);
//    }
//
//    @Override
//    protected int getResId() {
//        return R.layout.dialog_agreement;
//    }
//
//    @Override
//    protected void initView() {
//        DialogAgreementBinding binding= (DialogAgreementBinding) viewDataBinding;
//
//        SpannableStringBuilder style = new SpannableStringBuilder(binding.content.getText().toString());
//        style.setSpan(new ForegroundColorSpan(getContext().getColor(R.color.color_00a0f1)), 23, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        style.setSpan(new ForegroundColorSpan(getContext().getColor(R.color.color_00a0f1)), 30, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        binding.content.setText(style);
//
//        binding.content.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onConfirm(3);
//            }
//        });
//        binding.cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onConfirm(0);
//            }
//        });
//        binding.confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(listener!=null){
//                    listener.onConfirm(1);
//                }
//            }
//        });
//    }
//
//    @Override
//    protected int gravity() {
//        return Gravity.CENTER;
//    }
//
//    @Override
//    protected int width() {
//        return DensityUtils.dip2px(getContext(),490);
//    }
//
//    @Override
//    protected int height() {
//        return ViewGroup.LayoutParams.WRAP_CONTENT;
//    }
//
//    @Override
//    protected int designWidthInDp() {
//        return designWidthInDp;
//    }
//}
