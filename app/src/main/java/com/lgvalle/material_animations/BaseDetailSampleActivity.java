package com.lgvalle.material_animations;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lgvalle on 04/09/15.
 */
public abstract class BaseDetailSampleActivity extends AppCompatActivity {

    protected Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    abstract int getContentView();

}
