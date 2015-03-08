package com.lgvalle.material_animations;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Fade;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Explode ex = new Explode();
        ex.setDuration(2000);
        getWindow().setEnterTransition(ex);

        Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setReturnTransition(fade);
    }
}
