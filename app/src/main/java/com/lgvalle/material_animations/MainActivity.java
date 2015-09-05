package com.lgvalle.material_animations;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Sample> samples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setupWindowAnimations();
        setupSamples();
        setupLayout();
    }

    private void setupWindowAnimations() {
        // ReEnter transition is executed when returning to this activity
        Slide reenterTransition = new Slide();
        reenterTransition.setSlideEdge(Gravity.START);
        reenterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(reenterTransition);
    }

    private void setupSamples() {
        samples = Arrays.asList(
                new Sample(Color.RED, "Exit/Enter Transition Sample"),
                new Sample(Color.BLUE, "Shared element Transition Sample"),
                new Sample(Color.GREEN, "View animations Sample"),
                new Sample(Color.YELLOW, "Circular Reveal Transition Sample")
        );
    }

    private Drawable getSampleDrawable() {
        return ContextCompat.getDrawable(this, R.drawable.circle_24dp);
    }

    private void setupLayout() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SamplesRecyclerAdapter samplesRecyclerAdapter = new SamplesRecyclerAdapter(this, samples);
        recyclerView.setAdapter(samplesRecyclerAdapter);
    }

    private void setViewWidth(View view, int x) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = x;
        view.setLayoutParams(params);
    }
}
