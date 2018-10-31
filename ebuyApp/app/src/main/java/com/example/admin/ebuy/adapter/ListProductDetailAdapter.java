package com.example.admin.ebuy.adapter;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.activity.HomeActivity;
import com.example.admin.ebuy.activity.SupportActivity;
import com.example.admin.ebuy.base.BaseFragment;
import com.example.admin.ebuy.model.ProductDetailData;
import com.example.admin.ebuy.model.home.ProductDetailFragment;
import com.example.admin.ebuy.model.respon.ProductDetailResponse;
import com.example.admin.ebuy.util.Navigator;
import com.example.admin.ebuy.view.EBCustomFont;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListProductDetailAdapter extends BaseAdapter {
    private ArrayList<ProductDetailData> lisProductDetail;
    LayoutInflater layoutInflater;
    private BaseFragment baseFragment;

    public void setLisProductDetail(ArrayList<ProductDetailData> lisProductDetail) {
        this.lisProductDetail = lisProductDetail;
    }



    public ListProductDetailAdapter(ArrayList<ProductDetailData> lisProductDetail, BaseFragment baseFragment) {
        this.lisProductDetail = lisProductDetail;
        this.baseFragment = baseFragment;
        this.layoutInflater = LayoutInflater.from(baseFragment.getContext());
    }

    @Override
    public int getCount() {
        if(lisProductDetail!=null)
        {
            return lisProductDetail.size();
        }
       return 0;
    }

    @Override
    public Object getItem(int i) {
        return lisProductDetail.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lisProductDetail.get(i).getId_detail();
    }

    @Override
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        EBCustomFont txtNamePro, txtPricePro;
        ImageView imageView;



            view = layoutInflater.inflate(R.layout.product_detail_item, viewGroup, false);
            txtNamePro = (EBCustomFont) view.findViewById(R.id.txtNamePro);
            txtPricePro = (EBCustomFont) view.findViewById(R.id.pricePro);
            imageView = (ImageView)view.findViewById(R.id.imgProduct);


        Picasso.with(baseFragment.getContext())
                .load(lisProductDetail.get(i).getImage())
                .placeholder(R.mipmap.logo)
                .into(imageView);
        txtNamePro.setText(lisProductDetail.get(i).getName());
        txtPricePro.setText(lisProductDetail.get(i).getPrice()+"");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                String data = gson.toJson(lisProductDetail.get(i));
                Bundle bundle = new Bundle();
                bundle.putString("data",data);
                Navigator.getInstance().startFragment(baseFragment.getContext(),ProductDetailFragment.TAG,SupportActivity.class,bundle);
            }
        });
        return view;
    }


}

