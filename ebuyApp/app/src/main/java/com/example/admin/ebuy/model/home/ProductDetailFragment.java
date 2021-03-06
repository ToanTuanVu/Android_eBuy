package com.example.admin.ebuy.model.home;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.ebuy.R;
import com.example.admin.ebuy.activity.SupportActivity;
import com.example.admin.ebuy.adapter.FeedbackAdapter;
import com.example.admin.ebuy.adapter.ListProductAdapter;
import com.example.admin.ebuy.adapter.ListProductDetailAdapter;
import com.example.admin.ebuy.base.BaseActivity;
import com.example.admin.ebuy.base.BaseFragment;
import com.example.admin.ebuy.model.ProductDetailData;
import com.example.admin.ebuy.model.respon.CustomerRespose;
import com.example.admin.ebuy.model.respon.FeedBackResponse;
import com.example.admin.ebuy.model.respon.ProductDetailResponse;
import com.example.admin.ebuy.network.EBServices;
import com.example.admin.ebuy.network.ServiceFactory;
import com.example.admin.ebuy.util.AppConfig;
import com.example.admin.ebuy.util.WriteLog;
import com.example.admin.ebuy.view.EBCustomFont;
import com.example.admin.ebuy.view.MyGridview;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProductDetailFragment extends BaseFragment {
    public static final String TAG = "ProductDetailFragment";
    EBCustomFont txtpricePro, txtDanhmuc, txtThuonhieu, txtChatlieu, txtGuitu, txtDetailPro,txtNumLike,txtNumStar;
    TextView txtNamePro,txtNameShop,txtAddressShop;
    CircleImageView imgAvatarShop;
    ImageView imgview;
    FeedbackAdapter feedbackAdapter;
    RecyclerView recyclerViewCommet,recyclerViewPro;
    LinearLayoutManager linearLayoutManager,linearLayoutManagerHorizontal;

    ListProductAdapter listProductAdapter;
    LinearLayout linearLayout;
    GestureDetector gestureDetector;
    int SWIPE_THERSHOLD=70;
    int SWIPE_VELOCITY =70;
    RatingBar ratingBar;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.product_detail_fragment;
    }

    @Override
    protected void onSetBodyView(View view, ViewGroup container, Bundle savedInstanceState) {

        mapped(view);
        Gson gson = new Gson();
        savedInstanceState = getActivity().getIntent().getExtras();
        ProductDetailData productDetailData = gson.fromJson(savedInstanceState.getString("data").toString(), ProductDetailData.class);
        Picasso.with(getContext())
                .load(productDetailData.getImage())
                .placeholder(R.mipmap.logo)
                .into(imgview);
        txtThuonhieu.setText(productDetailData.getTrademark());
        txtpricePro.setText(productDetailData.getPrice() + "");
        txtNamePro.setText(productDetailData.getName());
        txtGuitu.setText(productDetailData.getAddress());
        txtChatlieu.setText(productDetailData.getMaterial());
        txtDetailPro.setText(productDetailData.getDescribe());
        txtNumLike.setText(productDetailData.getNumLike()+"");
        txtNumStar.setText("("+productDetailData.getNumFeedback()+")");
        ratingBar.setRating(productDetailData.getNumStar());


        feedbackAdapter = new FeedbackAdapter(this);
        linearLayoutManagerHorizontal = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), linearLayoutManager.getOrientation());
        recyclerViewCommet.addItemDecoration(dividerItemDecoration);



        listProductAdapter = new ListProductAdapter(this);


        ((BaseActivity) getActivity()).setTitle(true, productDetailData.getName());
        gestureDerector();
        getCustomerByID(productDetailData.getId_product());
        getFeedbackByIDProduct(productDetailData.getId_product());
        getListProductDetailByTypeProduct(productDetailData.getId_type());

    }

    @Override
    public String getTagName() {
        return TAG;
    }

    private void mapped(View view) {
        imgview = (ImageView) view.findViewById(R.id.imgview);
        txtChatlieu = (EBCustomFont) view.findViewById(R.id.txtChatlieu);
        txtDanhmuc = (EBCustomFont) view.findViewById(R.id.txtDanhmuc);
        txtDetailPro = (EBCustomFont) view.findViewById(R.id.txtDetailPro);
        txtGuitu = (EBCustomFont) view.findViewById(R.id.txtGuitu);
        txtNamePro = (TextView) view.findViewById(R.id.txtNamePro);
        txtpricePro = (EBCustomFont) view.findViewById(R.id.txtpricePro);
        txtThuonhieu = (EBCustomFont) view.findViewById(R.id.txtThuonhieu);
        txtNameShop = (EBCustomFont)view.findViewById(R.id.txtNameShop);
        txtAddressShop = (EBCustomFont)view.findViewById(R.id.txtAddress);
        imgAvatarShop = (CircleImageView)view.findViewById(R.id.imgAvatarShop);
        recyclerViewCommet = (RecyclerView)view.findViewById(R.id.recyFeedbackList);
        recyclerViewPro = (RecyclerView)view.findViewById(R.id.recyclerviewPro);
        linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
        txtNumLike = (EBCustomFont)view.findViewById(R.id.txtNumLike);
        txtNumStar = (EBCustomFont)view.findViewById(R.id.txtNumStar);
        ratingBar = (RatingBar)view.findViewById(R.id.ratingBar);

    }
    private void gestureDerector()
    {
        gestureDetector = new GestureDetector(getActivity(),new ProductDetailFragment.MyGesture());
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gestureDetector.onTouchEvent(motionEvent);

                return true;
            }
        });
    }
    public class MyGesture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if(e2.getX() - e1.getX() >SWIPE_THERSHOLD && Math.abs(velocityX) >SWIPE_VELOCITY)
            {
                getActivity().onBackPressed();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    private void getCustomerByID(int id) {
        ServiceFactory.createRetrofitService(EBServices.class, AppConfig.getApiEndpoint())
                .getCustomerByIdProduct(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CustomerRespose>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CustomerRespose customerRespose) {
                        txtAddressShop.setText(customerRespose.getData().getAddress());
                        txtNameShop.setText(customerRespose.getData().getName());
                        Picasso.with(getContext())
                                .load(customerRespose.getData().getImg())
                                .placeholder(R.mipmap.logo)
                                .into(imgAvatarShop);

                    }
                });


    }
    private void getFeedbackByIDProduct(int id) {
        ServiceFactory.createRetrofitService(EBServices.class, AppConfig.getApiEndpoint())
                .getFeedbackByIdProduct(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FeedBackResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FeedBackResponse feedBackResponse) {
                        WriteLog.e("TAG", feedBackResponse.toString());
                        recyclerViewCommet.setHasFixedSize(true);
                        recyclerViewCommet.setLayoutManager(linearLayoutManager);
                        feedbackAdapter.setListFeedBackData(feedBackResponse.getData());
                        recyclerViewCommet.setAdapter(feedbackAdapter);
                    }
                });

    }

    private void getListProductDetailByTypeProduct(int data)
    {
        ServiceFactory.createRetrofitService(EBServices.class,AppConfig.getApiEndpoint())
                .getProductDetailByTypeProduct(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductDetailResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ProductDetailResponse productDetailResponse) {
                        WriteLog.e("TAG", productDetailResponse.toString());
                        recyclerViewPro.setHasFixedSize(true);
                        recyclerViewPro.setLayoutManager(linearLayoutManagerHorizontal);

                        listProductAdapter.setListProduct(productDetailResponse.getData());
                        recyclerViewPro.setAdapter(listProductAdapter);


                    }
                });
    }
}
