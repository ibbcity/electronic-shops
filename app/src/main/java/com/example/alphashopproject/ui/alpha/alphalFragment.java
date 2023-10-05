package com.example.alphashopproject.ui.alpha;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.alphashopproject.R;
import com.bumptech.glide.Glide;
import com.example.alphashopproject.CategoryAdapter;
import com.example.alphashopproject.CategoryModel;
import com.example.alphashopproject.DBquerries;
import com.example.alphashopproject.HorizontalProductScrollModel;
import com.example.alphashopproject.MainActivity;
import com.example.alphashopproject.alphaAdapter;
import com.example.alphashopproject.alphalModel;
import com.example.alphashopproject.SliderModel;
import com.example.alphashopproject.WishlistModel;

import java.util.ArrayList;
import java.util.List;


import static  com.example.alphashopproject.DBquerries.categoryModelList;
import static  com.example.alphashopproject.DBquerries.lists;
import static  com.example.alphashopproject.DBquerries.loadCategories;
import static  com.example.alphashopproject.DBquerries.loadFragmentData;
import static  com.example.alphashopproject.DBquerries.loadedCategoriesNames;

public class alphalFragment extends Fragment {

    public static SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView categoryRecyclerView , homepageRecyclerview;
    private CategoryAdapter categoryAdapter;
    private alphaAdapter alphaAdapter;
    private ImageView noInternet;
    private Button retryBtn;

    private final List<CategoryModel> categoryModelFakeList=new ArrayList<>();
    private final List<alphalModel> alphalModelFakeList =new ArrayList<>();


    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;

    public alphalFragment(){

    }

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_alpha, container, false);
        swipeRefreshLayout=view.findViewById(R.id.refresh_layout);
        noInternet=view.findViewById(R.id.no_internet);
        retryBtn=view.findViewById(R.id.retry_btn);
        homepageRecyclerview =view.findViewById(R.id.my_mall_recyclerview);
        categoryRecyclerView=view.findViewById(R.id.category_recycler_view);
        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.colorPrimary),getContext().getResources().getColor(R.color.colorPrimary),getContext().getResources().getColor(R.color.colorPrimary));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homepageRecyclerview.setLayoutManager(testingLayoutManager);


//////////category fake list
        categoryModelFakeList.add(new CategoryModel("null",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));
        categoryModelFakeList.add(new CategoryModel("",""));

//////////category fake list


//////////home page fake list

        List<SliderModel> sliderModelFakeList=new ArrayList<>();

        sliderModelFakeList.add(new SliderModel("null","#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null","#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null","#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null","#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null","#dfdfdf"));

        List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList=new ArrayList<>();
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));

        alphalModelFakeList.add(new alphalModel(0,sliderModelFakeList));
        alphalModelFakeList.add(new alphalModel(1,"","#dfdfdf"));
        alphalModelFakeList.add(new alphalModel(2,"","#dfdfdf",horizontalProductScrollModelFakeList,new ArrayList<WishlistModel>()));
        alphalModelFakeList.add(new alphalModel(3,"","#dfdfdf",horizontalProductScrollModelFakeList));

//////////home page fake list

        categoryAdapter= new CategoryAdapter(categoryModelFakeList);
        alphaAdapter =new alphaAdapter(alphalModelFakeList);

        connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()==true){
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternet.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homepageRecyclerview.setVisibility(View.VISIBLE);

            if(categoryModelList.size() == 0){
                loadCategories(categoryRecyclerView,getContext());
            }else {
                categoryAdapter=new CategoryAdapter(categoryModelList);
                categoryAdapter.notifyDataSetChanged();
            }
            categoryRecyclerView.setAdapter(categoryAdapter);

            if(lists.size() == 0){
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<alphalModel>());
                loadFragmentData(homepageRecyclerview,getContext(),0,"Home");
            }else {
                alphaAdapter =new alphaAdapter(lists.get(0));
                alphaAdapter.notifyDataSetChanged();
            }
            homepageRecyclerview.setAdapter(alphaAdapter);
        }
        else {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            categoryRecyclerView.setVisibility(View.GONE);
            homepageRecyclerview.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.no_internet).into(noInternet);
            noInternet.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);

        }

        ///refresh layout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });
        ///refresh layout

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadPage();
            }
        });

        reloadPage();
        return view;
    }

    private void reloadPage(){
        networkInfo = connectivityManager.getActiveNetworkInfo();
//        categoryModelList.clear();
//        lists.clear();
//        loadedCategoriesNames.clear();
        DBquerries.clearData();
        if(networkInfo != null && networkInfo.isConnected()==true){
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            noInternet.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homepageRecyclerview.setVisibility(View.VISIBLE);
            categoryAdapter=new CategoryAdapter(categoryModelFakeList);
            alphaAdapter =new alphaAdapter(alphalModelFakeList);
            categoryRecyclerView.setAdapter(categoryAdapter);
            homepageRecyclerview.setAdapter(alphaAdapter);


            loadCategories(categoryRecyclerView,getContext());
            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<alphalModel>());
            loadFragmentData(homepageRecyclerview,getContext(),0,"Home");
        }else {
            MainActivity.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            Toast.makeText(getContext(),"No internet Connction!",Toast.LENGTH_SHORT).show();
            categoryRecyclerView.setVisibility(View.GONE);
            homepageRecyclerview.setVisibility(View.GONE);
            Glide.with(getContext()).load(R.drawable.no_internet).into(noInternet);
            noInternet.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}