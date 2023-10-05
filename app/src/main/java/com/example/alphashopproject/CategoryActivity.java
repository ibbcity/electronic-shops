package com.example.alphashopproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.alphashopproject.DBquerries.lists;
import static com.example.alphashopproject.DBquerries.loadFragmentData;
import static com.example.alphashopproject.DBquerries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {
    private String titles;
    private RecyclerView categoryRecyclerView;
    alphaAdapter alphaAdapter;

    //private List<CategoryModel> categoryModelFakeList=new ArrayList<>();
    private List<alphalModel> alphalModelFakeList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title=getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

//////////home page fake list

        List<SliderModel> sliderModelFakeList=new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("null","#ffffff"));
        sliderModelFakeList.add(new SliderModel("null","#ffffff"));
        sliderModelFakeList.add(new SliderModel("null","#ffffff"));
        sliderModelFakeList.add(new SliderModel("null","#ffffff"));
        sliderModelFakeList.add(new SliderModel("null","#ffffff"));

        List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList=new ArrayList<>();
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));

        alphalModelFakeList.add(new alphalModel(0,sliderModelFakeList));
        alphalModelFakeList.add(new alphalModel(1,"","#ffffff"));
        alphalModelFakeList.add(new alphalModel(2,"","#ffffff",horizontalProductScrollModelFakeList,new ArrayList<WishlistModel>()));
        alphalModelFakeList.add(new alphalModel(3,"","#ffffff",horizontalProductScrollModelFakeList));



//////////home page fake list



        categoryRecyclerView=findViewById(R.id.category_recycler_view);

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);
        alphaAdapter =new alphaAdapter(alphalModelFakeList);
           int listPosition = 0;
           for (int x = 0; x < loadedCategoriesNames.size(); x++) {
               if (loadedCategoriesNames.get(x).equals(title.toUpperCase())) {
                   listPosition = x;
               }
           }
           if (listPosition == 0) {
               loadedCategoriesNames.add(title.toUpperCase());
               lists.add(new ArrayList<alphalModel>());
               loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, title);
           } else {
               alphaAdapter = new alphaAdapter(lists.get(listPosition));

           }
           if(title.equals(getString(R.string.homes))){
               titles = "Home";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }
           else if(title.equals(getString(R.string.elctironics))){
               titles = "Electronics";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }
           else if(title.equals(getString(R.string.Furnitur))){
               titles = "Furniture";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }
           else if(title.equals(getString(R.string.Fashion))){
               titles = "Fashion";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }else if(title.equals(getString(R.string.Books))){
               titles = "Books";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }else if(title.equals(getString(R.string.Sports))){
               titles = "Sports";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }else if(title.equals(getString(R.string.Toys))){
               titles = "Toys";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }else if(title.equals(getString(R.string.Shoes))){
               titles = "Shoes";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }else if(title.equals(getString(R.string.Accessories))){
               titles = "Accessories";
               for (int x = 0; x < loadedCategoriesNames.size(); x++) {
                   if (loadedCategoriesNames.get(x).equals(titles.toUpperCase())) {
                       listPosition = x;
                   }
               }
               if (listPosition == 0) {
                   loadedCategoriesNames.add(titles.toUpperCase());
                   lists.add(new ArrayList<alphalModel>());
                   loadFragmentData(categoryRecyclerView, this, loadedCategoriesNames.size() - 1, titles);
               } else {
                   alphaAdapter = new alphaAdapter(lists.get(listPosition));

               }
           }

        categoryRecyclerView.setAdapter(alphaAdapter);
        alphaAdapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.main_search_icon){
            startActivity(new Intent(CategoryActivity.this, SearchActivity.class));
            return true;
        }else if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
