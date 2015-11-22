package com.lgvalle.material_animations;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DemoActivityB_StepN extends AppCompatActivity {
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
        setContentView(R.layout.activity_b_step1);
        ButterKnife.bind(this);
        setupWindowAnimations();
        setupSamples();
        setupToolbar();
        setupLayout();
    }

    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        //Fade fade = new Fade();
        //fade.setDuration(1000);


        //Slide slide = new Slide(Gravity.RIGHT);
        final Visibility fade = new Fade();
        fade.setDuration(500);
        fade.excludeTarget(R.id.toolbar, true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        fade.excludeTarget(R.id.demo_fab, true);

        getWindow().setEnterTransition(fade);
        //getWindow().setEnterTransition(slide);


        getWindow().getSharedElementReturnTransition().setDuration(1000);
        getWindow().setAllowReturnTransitionOverlap(false);
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setEnterTransition(fade);


        Transition f = getWindow().getEnterTransition();
        f.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                //TransitionManager.beginDelayedTransition(viewRoot);
                //fab.setScaleX(1);
                //fab.setScaleY(1);

                fab.animate()
                        .scaleX(1)
                        .scaleY(1)
                        .setDuration(500);

                fade.removeListener(this);
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


    @OnClick(R.id.demo_fab)
    public void onClickFab() {
        int transitionId = R.transition.changebounds_with_arcmotion;
        TransitionInflater inflater = TransitionInflater.from(this);
        Transition transition = inflater.inflateTransition(R.transition.changebounds_with_arcmotion);
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

    private Animator animateRevealColorFromCoordinates(ViewGroup viewRoot, @ColorRes int color, int x, int y) {
        float finalRadius = (float) Math.hypot(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, 0, finalRadius);
        viewRoot.setBackgroundColor(ContextCompat.getColor(this, color));
        viewRoot.setVisibility(View.VISIBLE);
        anim.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }

    private Animator animateRevealFromCoordinates(ViewGroup viewRoot) {
        int x = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int y = viewRoot.getBottom() - fab.getHeight();
        float finalRadius = (float) Math.max(viewRoot.getWidth(), viewRoot.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(viewRoot, x, y, fab.getWidth(), finalRadius);
        viewRoot.setVisibility(View.VISIBLE);
        //anim.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        //anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.start();
        return anim;
    }

    @Override
    public void onBackPressed() {
        fab.animate()
                .scaleX(0f)
                .scaleY(0f)
                .setDuration(400)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        finishAfterTransition();
                    }
                });
    }
}
