package com.example.admin.ebuy.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.adapter.HomeAdapter;
import com.example.admin.ebuy.base.BaseActivity;

public class HomeActivity extends BaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {
    private ViewPager contentView;
    private HomeAdapter homeAdapter;
    private LinearLayout btnHome,btnShopping,btnUser,btnList;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void loadControl(Bundle savedInstanceState) {
        homeAdapter = new HomeAdapter(getSupportFragmentManager(), this);
        contentView = (ViewPager)findViewById(R.id.contentView);
        contentView.setAdapter(homeAdapter);
        contentView.setOffscreenPageLimit(4);


        btnHome= (LinearLayout) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        btnList=(LinearLayout) findViewById(R.id.btnList);
        btnList.setOnClickListener(this);
        btnShopping=(LinearLayout) findViewById(R.id.btnShopping);
        btnShopping.setOnClickListener(this);
        btnUser=(LinearLayout) findViewById(R.id.btnUser);
        btnUser.setOnClickListener(this);


        startFirstFragment();
    }

    @Override
    public int getFragmentContainerViewId() {
        return R.id.contentView;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                contentView.setCurrentItem(0);
                break;
            case 1:
                contentView.setCurrentItem(1);
                break;
            case 2:
                contentView.setCurrentItem(2);
                break;
            case 3:
                contentView.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHome:
                contentView.setCurrentItem(0);
                break;
            case R.id.btnList:
                contentView.setCurrentItem(1);
                break;
            case R.id.btnShopping:
                contentView.setCurrentItem(2);
                break;
            case R.id.btnUser:
                contentView.setCurrentItem(3);
                break;
        }
    }
}
