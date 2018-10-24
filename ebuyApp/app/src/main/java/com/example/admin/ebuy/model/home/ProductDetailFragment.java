package com.example.admin.ebuy.model.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.base.BaseFragment;

public class ProductDetailFragment extends BaseFragment {
    public static final String TAG="ProductDetailFragment";
    @Override
    protected int getLayoutResourceId() {
        return R.layout.product_detail_fragment;
    }

    @Override
    protected void onSetBodyView(View view, ViewGroup container, Bundle savedInstanceState) {

        savedInstanceState=getActivity().getIntent().getExtras();
        Toast.makeText(getContext(), savedInstanceState.getString("data").toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public String getTagName() {
        return TAG;
    }
}
