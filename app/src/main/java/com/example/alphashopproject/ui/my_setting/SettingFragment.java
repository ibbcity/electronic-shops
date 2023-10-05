package com.example.alphashopproject.ui.my_setting;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alphashopproject.MainActivity;
import com.example.alphashopproject.R;
import com.example.alphashopproject.RegisterActivity;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class SettingFragment extends Fragment {
    public static boolean language = true;
    public SettingFragment() {
    }
    private Button english,arabic;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        english = root.findViewById(R.id.ln_english);
        arabic = root.findViewById(R.id.ln_arabic);
        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setlanguage("ar");
                language =false;

            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setlanguage("en");
                language = true;
            }
        });
        return root;
    }
    private void setlanguage(String lang){
        Locale mylocale = new Locale(lang);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = mylocale;
        getResources().updateConfiguration(conf,dm);
        Intent refreash = new Intent(getContext(), MainActivity.class);
        getContext().startActivity(refreash);
    }
}