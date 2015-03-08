package com.lgvalle.material_animations;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Fade;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setupLayout();
        setupWindowAnimations();
    }

    private void setupLayout() {

    }

    private void setupWindowAnimations() {
        Explode ex = new Explode();
        ex.setDuration(2000);
        getWindow().setExitTransition(ex);
        getWindow().setEnterTransition(ex);
    }


}
