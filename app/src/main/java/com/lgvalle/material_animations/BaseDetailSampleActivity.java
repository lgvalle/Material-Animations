package com.lgvalle.material_animations;

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
        setContentView(getContentView());
        sample = (Sample) getIntent().getExtras().getSerializable("sample");
    }

    abstract int getContentView();

}
