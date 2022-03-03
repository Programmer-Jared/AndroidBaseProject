//package com.jared.base.adapter;
//
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.databinding.DataBindingUtil;
//import androidx.databinding.ViewDataBinding;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewbinding.ViewBinding;
//
//import com.jared.base.adapter.BaseViewHolder;
//import com.jared.base.listener.OnItemClickListener;
//import com.jared.base.utils.ReflectUtil;
//
//import me.jessyan.autosize.AutoSizeCompat;
///**
// * @ClassName BaseRecyclerViewAdapter
// * @Author Jared
// * @Date 2022/3/2 16:12
// * @Version 1.0
// * @Description todo 考虑多TYPE布局adapter封装 优化 适配AUTOSIZE
// */
//public abstract class BaseRecyclerViewAdapter<VB extends ViewBinding> extends RecyclerView.Adapter<BaseViewHolder> {
//    protected OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//
//    protected abstract void bindData(VB binding, int position);
//
//    @NonNull
//    @Override
//    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        VB binding = ReflectUtil.getInflate(getClass(),LayoutInflater.from(parent.getContext()),parent);
//        // TODO: 2022/3/3 适配autosize
////        AutoSizeCompat.autoConvertDensityBaseOnWidth(parent.getResources(), designWidthInDp());
//        return new BaseViewHolder(binding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
////        VB binding = holder.getBinding();
//        bindData(binding, position);
//        binding.executePendingBindings();
//    }
//
//    @Override
//    public int getItemCount() {
//        return getItemSize();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }
//}
