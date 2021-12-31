package com.example.lockv01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PasswordAdapter adapter;
    private ArrayList<Password> passwordArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new PasswordAdapter(passwordArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        passwordArrayList = new ArrayList<>();
        passwordArrayList.add(new Password("Facebook", "haha@gmail.com", "123456789"));
        passwordArrayList.add(new Password("Facebook", "1214234560", "987654321"));
        passwordArrayList.add(new Password("Facebook", "1214230345", "987648765"));
        passwordArrayList.add(new Password("Facebook", "1214378098", "098758124"));
    }

}
