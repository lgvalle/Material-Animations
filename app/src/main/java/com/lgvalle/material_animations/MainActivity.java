package com.lgvalle.material_animations;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends ActionBarActivity {

    private ViewGroup sceneRoot;
    private View squareRed;
    private View squareBlue;
    private View squareGreen;
    private View squareOrange;


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
        getWindow().setReenterTransition(fade);
    }

    private void setupLayout() {
        sceneRoot = (LinearLayout) findViewById(R.id.scene_root);

        squareRed = findViewById(R.id.square_red);
        squareRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetailActivity1.class);
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(i, transitionActivityOptions.toBundle());
            }
        });

        squareBlue = findViewById(R.id.square_blue);
        squareBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetailActivity2.class);

                View sharedView = squareBlue;
                String transitionName = getString(R.string.square_blue_name);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
                startActivity(i, transitionActivityOptions.toBundle());
            }
        });

        squareGreen = findViewById(R.id.square_green);
        squareGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(sceneRoot);
                setViewWidth(squareRed, 500);
                setViewWidth(squareBlue, 500);
                setViewWidth(squareGreen, 500);
                setViewWidth(squareOrange, 500);
            }
        });

        squareOrange = findViewById(R.id.square_orange);
        squareOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DetailActivity3.class);

                View sharedView = squareOrange;
                String transitionName = getString(R.string.square_orange_name);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, sharedView, transitionName);
                startActivity(i, transitionActivityOptions.toBundle());
            }
        });
    }

    private void setViewWidth(View view, int x) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = x;
        view.setLayoutParams(params);
    }
}
