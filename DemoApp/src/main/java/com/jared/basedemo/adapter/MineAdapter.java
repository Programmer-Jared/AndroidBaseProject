//package com.jared.basedemo.adapter;
//
//import static com.reachauto.xg9phoneproject.Constant.designWidthInDp;
//
//import androidx.databinding.ViewDataBinding;
//
//import com.jared.base.adapter.BaseRecyclerViewAdapter;
//import com.reachauto.xg9phoneproject.R;
//import com.reachauto.xg9phoneproject.databinding.AdapterConfigurationContentBinding;
//import com.reachauto.xg9phoneproject.model.bean.PreferencesBean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MineAdapter extends BaseRecyclerViewAdapter {
//    private List<PreferencesBean> preferencesBeanList = new ArrayList<>();
//
//    public void setPreferencesBeanList(List<PreferencesBean> preferencesBeanList) {
//        this.preferencesBeanList = preferencesBeanList;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.adapter_configuration_content;
//    }
//
//    @Override
//    protected int getItemSize() {
//        return preferencesBeanList.size();
//    }
//
//    @Override
//    protected int designWidthInDp() {
//        return designWidthInDp;
//    }
//
//    @Override
//    protected void bindData(ViewDataBinding viewDataBinding, int position) {
//        AdapterConfigurationContentBinding binding = (AdapterConfigurationContentBinding) viewDataBinding;
//        binding.contentName.setText(preferencesBeanList.get(position).getName());
//        binding.contentCondition.setText(new StringBuilder().append(preferencesBeanList.get(position).getParam()).append(preferencesBeanList.get(position).getUnit()).toString());
//        binding.contentType.setText(preferencesBeanList.get(position).getType());
//        binding.getRoot().setOnClickListener(v -> onItemClickListener.onClick(position));
//    }
//}
