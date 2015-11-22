package com.lgvalle.material_animations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DemoActivityA_Step1 extends AppCompatActivity {
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.card_view)
    CardView cardView;
    @Bind(R.id.card_view2)
    CardView cardView2;
    @Bind(R.id.card_view3)
    CardView cardView3;
    @Bind(R.id.card_view4)
    CardView cardView4;
    private List<Sample> samples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_step1);
        ButterKnife.bind(this);
        setupWindowAnimations();
        setupToolbar();
        setupLayout();
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Transition explode = new Explode();
        explode.setDuration(1000);
        getWindow().setExitTransition(explode);
    }


    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTitle("");
    }

    private void setupLayout() {
    }


    @OnClick({R.id.card_view, R.id.card_view2, R.id.card_view3, R.id.card_view4})
    public void clickOnCard() {

        Intent i = new Intent(this, DemoActivityB_Step1.class);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, null);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    @NonNull
    private RecyclerView.LayoutManager getLayoutManager() {
        //return new GridLayoutManager(this, 3);
        return new LinearLayoutManager(this);
    }
}
