package com.lgvalle.material_animations;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class DetailActivity1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setEnterTransition(explode);
    }
}
