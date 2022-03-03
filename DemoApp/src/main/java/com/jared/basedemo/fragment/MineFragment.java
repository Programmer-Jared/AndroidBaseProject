//package com.jared.basedemo.fragment;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.jared.base.mvpbase.BasePresenter;
//import com.jared.base.listener.OnItemClickListener;
//import com.jared.base.fragment.BaseFragment;
//import com.reachauto.xg9phoneproject.R;
//import com.reachauto.xg9phoneproject.databinding.FragmentMineBinding;
//import com.reachauto.xg9phoneproject.model.bean.PreferencesBean;
//import com.reachauto.xg9phoneproject.ui.adapter.MineAdapter;
//import com.reachauto.xg9phoneproject.ui.dialog.CustomWheelDialog;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MineFragment extends BaseFragment {
//    FragmentMineBinding binding;
//    MineAdapter adapter;
//    private List<PreferencesBean> preferencesBeanList = new ArrayList<>();
//    private CustomWheelDialog customWheelDialog;
//
//    @Override
//    protected int getLayoutResource() {
//        return R.layout.fragment_mine;
//    }
//
//    @Override
//    public void initView() {
//        super.initView();
//        binding = (FragmentMineBinding) viewDataBinding;
//        binding.titleView.setTitle("我的");
//        adapter = new MineAdapter();
//        binding.recycle.setLayoutManager(new LinearLayoutManager(context));
//        binding.recycle.setAdapter(adapter);
//        customWheelDialog = new CustomWheelDialog(context);
//    }
//
//    @Override
//    public void initData() {
//        super.initData();
//        preferencesBeanList.add(new PreferencesBean("空调温度", "24", "车内空调舒适温度设置", "℃"));
//        preferencesBeanList.add(new PreferencesBean("风量级别", "2", "空调设置", "级"));
//        preferencesBeanList.add(new PreferencesBean("座椅靠背角度", "15", "座椅设置", "°"));
//        adapter.setPreferencesBeanList(preferencesBeanList);
//    }
//
//    @Override
//    public void setListener() {
//        customWheelDialog.setItemClickListener(new CustomWheelDialog.ItemClickListener() {
//            @Override
//            public void onClick(int position, String param) {
//                preferencesBeanList.get(position).setParam(param);
//                adapter.setPreferencesBeanList(preferencesBeanList);
//            }
//        });
//        adapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
////                BaseToast.showToast(String.valueOf(position));
//                customWheelDialog.setData(position, preferencesBeanList.get(position).getParam());
//                if (!customWheelDialog.isShowing()) {
//                    customWheelDialog.show();
//                }
//                switch (position) {
//                    case 0:
//                        break;
//                    case 1:
//
//                        break;
//
//                    case 2:
//
//                        break;
//
//                    default:
//
//                        break;
//                }
//            }
//        });
//    }
//
//    @Override
//    public BasePresenter initPresenter() {
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        if (customWheelDialog != null && customWheelDialog.isShowing()) {
//            customWheelDialog.dismiss();
//        }
//        super.onDestroy();
//    }
//}
