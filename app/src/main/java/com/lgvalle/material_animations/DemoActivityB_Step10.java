package com.lgvalle.material_animations;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * TRANSITION LISTENER - PARA SALIR
 */
public class DemoActivityB_Step10 extends AppCompatActivity {
    @Bind(R.id.demo_fab)
    View fab;
    @Bind(R.id.view_root)
    ViewGroup viewRoot;
    @Bind(R.id.sample_list)
    RecyclerView sampleList;
    @Bind(R.id.dummy_layer)
    ViewGroup dummyLayer;
    private List<Sample> samples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_step9);
        ButterKnife.bind(this);
        setupWindowAnimations();
        setupSamples();
        setupToolbar();
        setupLayout();
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Transition transition = new Fade();
        transition.setDuration(1000);
        excludeCommons(transition);
        getWindow().setEnterTransition(transition);
        getWindow().getSharedElementReturnTransition().setDuration(1000);

        final Transition f = getWindow().getSharedElementEnterTransition();
        f.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                //TransitionManager.beginDelayedTransition(viewRoot);
                fab.animate()
                        .scaleX(1)
                        .scaleY(1)
                        .alpha(1)
                        .setInterpolator(new FastOutSlowInInterpolator());
                f.removeListener(this);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    private void excludeCommons(Transition transition) {
        transition.excludeTarget(R.id.toolbar, true);
        transition.excludeTarget(android.R.id.statusBarBackground, true);
        transition.excludeTarget(android.R.id.navigationBarBackground, true);
    }

    private void setupSamples() {
        samples = Arrays.asList(
                new Sample(ContextCompat.getColor(this, R.color.sample_red), "Transitions"),
                new Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
                new Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
                new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation"),
                new Sample(ContextCompat.getColor(this, R.color.sample_red), "Transitions"),
                new Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
                new Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
                new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation"),
                new Sample(ContextCompat.getColor(this, R.color.sample_red), "Transitions"),
                new Sample(ContextCompat.getColor(this, R.color.sample_blue), "Shared Elements"),
                new Sample(ContextCompat.getColor(this, R.color.sample_green), "View animations"),
                new Sample(ContextCompat.getColor(this, R.color.sample_yellow), "Circular Reveal Animation")
        );
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupLayout() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sample_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(getLayoutManager());
        DemoRecyclerAdapter samplesRecyclerAdapter = new DemoRecyclerAdapter(this, samples);
        recyclerView.setAdapter(samplesRecyclerAdapter);
    }

    @NonNull
    private RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3);
        //return new LinearLayoutManager(this);
    }


    @Override
    public void onBackPressed() {
        fab.animate()
                .scaleX(0)
                .scaleY(0)
                .alpha(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        finishAfterTransition();
                    }
                });

    }

    @OnClick(R.id.demo_fab)
    public void onClickFab() {
        int transitionId = R.transition.changebounds_with_arcmotion;
        TransitionInflater inflater = TransitionInflater.from(this);
        Transition transition = inflater.inflateTransition(R.transition.changebounds_with_arcmotion);
        transition.setInterpolator(new FastOutSlowInInterpolator());
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                fab.setVisibility(View.GONE);
                animateRevealFromCoordinates(dummyLayer);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
        TransitionManager.beginDelayedTransition(viewRoot, transition);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        fab.setLayoutParams(layoutParams);
    }


    private Animator animateRevealFromCoordinates(ViewGroup viewRoot) {
        int x = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int y = viewRoot.getBottom() - fab.getHeight();
        float finalRadius = (float) Math.hypot(x, y);

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, fab.getWidth(), finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        anim.setInterpolator(new FastOutSlowInInterpolator());
        anim.start();
        return anim;
    }
}
