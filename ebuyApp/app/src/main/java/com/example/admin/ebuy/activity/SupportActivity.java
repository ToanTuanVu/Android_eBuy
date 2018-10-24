package com.example.admin.ebuy.activity;

import android.os.Bundle;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.base.BaseActivity;

public class SupportActivity extends BaseActivity {
    @Override
    public int getLayoutResource() {
        return R.layout.support_activity;
    }

    @Override
    public void loadControl(Bundle savedInstanceState) {

    }

    @Override
    public int getFragmentContainerViewId() {
        return R.id.frameSupport;
    }
}
