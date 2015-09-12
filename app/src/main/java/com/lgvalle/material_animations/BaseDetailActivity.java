package com.lgvalle.material_animations;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by lgvalle on 12/09/15.
 */
public class BaseDetailActivity extends AppCompatActivity {
    protected static final String EXTRA_SAMPLE = "sample";
    protected static final String EXTRA_TYPE = "type";
    protected static final int TYPE_PROGRAMMATICALLY = 0;
    protected static final int TYPE_XML = 1;

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
