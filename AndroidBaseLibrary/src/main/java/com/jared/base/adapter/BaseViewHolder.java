package com.jared.base.adapter;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

/**
 * @ClassName BaseViewHolder
 * @Author Jared
 * @Date 2022/3/2 16:12
 * @Version 1.0
 * @Description
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    ViewBinding binding;

    public BaseViewHolder(ViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

    }

    public ViewBinding getBinding() {
        return binding;
    }
}
