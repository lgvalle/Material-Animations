package com.lgvalle.material_animations;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends ActionBarActivity {

    private View squareRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        setupLayout();
    }

    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setExitTransition(explode);

        Fade fade = new Fade();
        fade.setDuration(2000);
        getWindow().setReenterTransition(fade);
    }

    private void setupLayout() {
        squareRed = findViewById(R.id.square_red);
        squareRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                ActivityOptions transitionActivityOptions =
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                //new Pair<View, String>(squareRed, getString(R.string.square_red_name))
                startActivity(i, transitionActivityOptions.toBundle());
            }
        });
    }
}
