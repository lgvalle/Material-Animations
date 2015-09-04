package com.lgvalle.material_animations;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
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

    private void setupSamples() {
        samples = Arrays.asList(
                new Sample(getSampleDrawable(), Color.RED, "Exit/Enter Transition Sample"),
                new Sample(getSampleDrawable(), Color.BLUE, "Shared element Transition Sample"),
                new Sample(getSampleDrawable(), Color.GREEN, "View animations Sample"),
                new Sample(getSampleDrawable(), Color.YELLOW, "Circular Reveal Transition Sample")
        );
    }

    private Drawable getSampleDrawable() {
        return ContextCompat.getDrawable(this, R.drawable.circle_24dp);
    }


    private void setupWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(2000);
        //getWindow().setExitTransition(explode);

        Fade fade = new Fade();
        //getWindow().setReenterTransition(fade);
    }

    private void setupLayout() {

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
