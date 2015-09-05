package com.lgvalle.material_animations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lgvalle.material_animations.databinding.ActivityDetails1Binding;

public class DetailActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetails1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_details2);
        Sample sample = (Sample) getIntent().getExtras().getSerializable("sample");
        binding.setDetails1Sample(sample);
    }

}
