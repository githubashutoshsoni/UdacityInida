package com.example.kakashi.tourguide;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this,HomeActivity.class));
        ViewPager viewPager=(ViewPager) findViewById(R.id.viewpager);
        CategoryAdapter adapter= new CategoryAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

}

