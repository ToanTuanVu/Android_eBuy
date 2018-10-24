package com.example.admin.ebuy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.base.BaseFragment;
import com.example.admin.ebuy.model.ProductData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListCategoryProductAdapter extends RecyclerView.Adapter<ListCategoryProductAdapter.ViewHolder> {
    private BaseFragment baseFragment;

    public ArrayList<ProductData> getListCategoryProduct() {
        return listCategoryProduct;
    }

    public void setListCategoryProduct(ArrayList<ProductData> listCategoryProduct) {
        this.listCategoryProduct = listCategoryProduct;
        notifyDataSetChanged();
    }



    private ArrayList<ProductData> listCategoryProduct;



    public ListCategoryProductAdapter(BaseFragment baseFragment, ArrayList<ProductData> listProduct) {
        this.baseFragment = baseFragment;
        this.listCategoryProduct = listProduct;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(baseFragment.getContext());
        View view = layoutInflater.inflate(R.layout.category_product_item, viewGroup,false);


        return new ListCategoryProductAdapter.ViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(baseFragment.getContext())
                .load(listCategoryProduct.get(i).getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.textView.setText(listCategoryProduct.get(i).getName());




    }

    @Override
    public int getItemCount() {
        return listCategoryProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imgCategoryProduct);
            textView = (TextView)itemView.findViewById(R.id.nameCategoryProduct);


        }

    }
}
