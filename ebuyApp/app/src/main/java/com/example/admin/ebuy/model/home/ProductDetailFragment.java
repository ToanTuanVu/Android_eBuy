package com.example.admin.ebuy.model.home;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.base.BaseFragment;

public class ProductDetailFragment extends BaseFragment {
    public static final String TAG="ProductDetailFragment";
    @Override
    protected int getLayoutResourceId() {
        return R.layout.list_product_fragment;
    }

    @Override
    protected void onSetBodyView(View view, ViewGroup container, Bundle savedInstanceState) {



    }

    @Override
    public String getTagName() {
        return null;
    }
}
