package com.example.alphashopproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alphashopproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.alphashopproject.R.string.cutted_prices;
import static com.example.alphashopproject.R.string.product_titles;

public class SearchActivity extends AppCompatActivity {

    private TextView textView;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_view);
        textView = findViewById(R.id.search_textView);
        recyclerView = findViewById(R.id.search_recyclerview);

        recyclerView.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        final List<WishlistModel> list = new ArrayList<>();
        final List<String> ids = new ArrayList<>();

        adapter = new Adapter(list, false);
        adapter.setFromSearch(true);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                list.clear();
                ids.clear();

                // final String[] tags = s.toLowerCase().split(" ");

                final String[] tags_ar = s.toLowerCase().split(" ");
                for (final String tag : tags_ar) {
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("PRODUCTS").whereArrayContains("tags_ar", tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    WishlistModel model = new WishlistModel(
                                            documentSnapshot.getId()
                                            , documentSnapshot.get("product_image_1").toString()
                                            , documentSnapshot.get("product_title_ar").toString()
                                            , (long) documentSnapshot.get("free_coupens_ar")
                                            , documentSnapshot.get("average_rating_ar").toString()
                                            , (long) documentSnapshot.get("total_ratings_ar")
                                            , documentSnapshot.get("product_price_ar").toString()
                                            , documentSnapshot.get("cutted_price_ar").toString()
                                            , (boolean) documentSnapshot.get("COD")
                                            , true
                                    );
                                    model.setTags((ArrayList<String>) documentSnapshot.get("tags_eng"));

                                    if (!ids.contains(model.getProductId())) {
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }
                                if (tag.equals(tags_ar[tags_ar.length - 1])) {

                                    if (list.size() == 0) {
                                        textView.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                    } else {
                                        textView.setVisibility(View.GONE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        adapter.getFilter().filter(s);
                                    }
                                }
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                list.clear();
                ids.clear();

                // final String[] tags = s.toLowerCase().split(" ");


                final String[] tags_eng = s.toLowerCase().split(" ");
                for (final String tag : tags_eng) {
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("PRODUCTS").whereArrayContains("tags_eng", tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    WishlistModel model = new WishlistModel(
                                            documentSnapshot.getId()
                                            , documentSnapshot.get("product_image_1").toString()
                                            , documentSnapshot.get("product_title").toString()
                                            , (long) documentSnapshot.get("free_coupens")
                                            , documentSnapshot.get("average_rating").toString()
                                            , (long) documentSnapshot.get("total_ratings")
                                            , documentSnapshot.get("product_price").toString()
                                            , documentSnapshot.get("cutted_price").toString()
                                            , (boolean) documentSnapshot.get("COD")
                                            , true
                                    );
                                    model.setTags((ArrayList<String>) documentSnapshot.get("tags_eng"));

                                    if (!ids.contains(model.getProductId())) {
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }
                                if (tag.equals(tags_eng[tags_eng.length - 1])) {

                                    if (list.size() == 0) {
                                        textView.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                    } else {
                                        textView.setVisibility(View.GONE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        adapter.getFilter().filter(s);
                                    }
                                }
                                final String[] tags_ar = s.toLowerCase().split(" ");
                                //  final String[] tags_ar = s.toLowerCase().split(" ");
                                for (final String tag : tags_ar) {
                                    tag.trim();
                                    FirebaseFirestore.getInstance().collection("PRODUCTS").whereArrayContains("tags_ar", tag)
                                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                                    WishlistModel model = new WishlistModel(
                                                            documentSnapshot.getId()
                                                            , documentSnapshot.get("product_image_1").toString()
                                                            , documentSnapshot.get("product_title_ar").toString()
                                                            , (long) documentSnapshot.get("free_coupens_ar")
                                                            , documentSnapshot.get("average_rating_ar").toString()
                                                            , (long) documentSnapshot.get("total_ratings_ar")
                                                            , documentSnapshot.get("product_price_ar").toString()
                                                            , documentSnapshot.get("cutted_price_ar").toString()
                                                            , (boolean) documentSnapshot.get("COD")
                                                            , true
                                                    );
                                                    model.setTags((ArrayList<String>) documentSnapshot.get("tags_ar"));

                                                    if (!ids.contains(model.getProductId())) {
                                                        list.add(model);
                                                        ids.add(model.getProductId());
                                                    }
                                                }
                                                if (tag.equals(tags_ar[tags_ar.length - 1])) {

                                                    if (list.size() == 0) {
                                                        textView.setVisibility(View.VISIBLE);
                                                        recyclerView.setVisibility(View.GONE);
                                                    } else {
                                                        textView.setVisibility(View.GONE);
                                                        recyclerView.setVisibility(View.VISIBLE);
                                                        adapter.getFilter().filter(s);
                                                    }
                                                }
                                            } else {
                                                String error = task.getException().getMessage();
                                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    class Adapter extends WishlistAdapter implements Filterable {

        private final List<WishlistModel> originalList;

        public Adapter(List<WishlistModel> wishlistModelList, Boolean wishlist) {
            super(wishlistModelList, wishlist);
            originalList = wishlistModelList;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    List<WishlistModel> filteredList = new ArrayList<>();
                    final String[] tags = charSequence.toString().toLowerCase().split(" ");

                    for (WishlistModel model : originalList) {
                        ArrayList<String> presentTags = new ArrayList<>();
                        for (String tag : tags) {
                            if (model.getTags().contains(tag)) {
                                presentTags.add(tag);
                            }
                        }
                        model.setTags(presentTags);
                    }
                    for (int i = tags.length; i > 0; i--) {
                        for (WishlistModel model : originalList) {
                            if (model.getTags().size() == i) {
                                filteredList.add(model);
                            }
                        }
                    }
                    filterResults.values = filteredList;
                    filterResults.count = filteredList.size();


                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                    if (filterResults.count > 0) {
                        setWishlistModelList((List<WishlistModel>) filterResults.values);
                    }

                    notifyDataSetChanged();
                }
            };
        }
    }
}
