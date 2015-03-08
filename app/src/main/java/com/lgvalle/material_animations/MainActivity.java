package com.lgvalle.material_animations;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private View squareRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        setupLayout();
    }

    private void setupWindowAnimations() {
        Fade ex = new Fade();
        ex.setDuration(2000);
        Explode expl = new Explode();
        expl.setDuration(2000);

        getWindow().setExitTransition(ex);
        getWindow().setEnterTransition(expl);


       getWindow().setReenterTransition(expl);
    }

    private void setupLayout() {
        squareRed = findViewById(R.id.square_red);
        squareRed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, DetailActivity.class);
        switch (v.getId()) {
            case R.id.square_red:
                ActivityOptions transitionActivityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(this);
                //new Pair<View, String>(squareRed, getString(R.string.square_red_name))
                startActivity(i, transitionActivityOptions.toBundle());
                break;
        }
    }
}
