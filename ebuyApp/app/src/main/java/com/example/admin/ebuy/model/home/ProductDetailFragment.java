package com.example.admin.ebuy.model.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.base.BaseActivity;
import com.example.admin.ebuy.base.BaseFragment;
import com.example.admin.ebuy.model.ProductData;
import com.example.admin.ebuy.model.ProductDetailData;
import com.example.admin.ebuy.view.EBCustomFont;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class ProductDetailFragment extends BaseFragment {
    public static final String TAG="ProductDetailFragment";
    EBCustomFont txtpricePro,txtDanhmuc,txtThuonhieu,txtChatlieu,txtGuitu,txtDetailPro;
    TextView txtNamePro;
    ImageView imgview;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.product_detail_fragment;
    }

    @Override
    protected void onSetBodyView(View view, ViewGroup container, Bundle savedInstanceState) {

        mapped(view);
        Gson gson =new Gson();
        savedInstanceState=getActivity().getIntent().getExtras();
        ProductDetailData productDetailData = gson.fromJson(savedInstanceState.getString("data").toString(),ProductDetailData.class);
        Picasso.with(getContext())
                .load(productDetailData.getImage())
                .placeholder(R.mipmap.logo)
                .into(imgview);
        txtThuonhieu.setText(productDetailData.getTrademark());
        txtpricePro.setText(productDetailData.getPrice());
        txtNamePro.setText(productDetailData.getName());
        txtGuitu.setText(productDetailData.getAddress());
        txtChatlieu.setText(productDetailData.getMaterial());



        Toast.makeText(getContext(),productDetailData.getName() , Toast.LENGTH_SHORT).show();

    }

    @Override
    public String getTagName() {
        return TAG;
    }
    private void mapped(View view)
    {
        imgview = (ImageView)view.findViewById(R.id.imgview);
        txtChatlieu = (EBCustomFont)view.findViewById(R.id.txtChatlieu);
        txtDanhmuc = (EBCustomFont)view.findViewById(R.id.txtDanhmuc);
        txtDetailPro = (EBCustomFont)view.findViewById(R.id.txtDetailPro);
        txtGuitu= (EBCustomFont)view.findViewById(R.id.txtGuitu);
        txtNamePro = (TextView)view.findViewById(R.id.txtNamePro);
        txtpricePro = (EBCustomFont)view.findViewById(R.id.txtpricePro);
        txtThuonhieu = (EBCustomFont)view.findViewById(R.id.txtThuonhieu);
    }
}
