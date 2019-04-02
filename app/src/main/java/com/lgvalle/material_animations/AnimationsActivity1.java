package com.lgvalle.material_animations;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lgvalle.material_animations.databinding.ActivityAnimations1Binding;

public class AnimationsActivity1 extends BaseDetailActivity {
    private ImageView square;
    private ViewGroup viewRoot;
    private boolean sizeChanged;
    private int savedWidth;
    private boolean positionChanged;
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void setupWindowAnimations() {
        getWindow().setReenterTransition(new Fade());
    }

    private void bindData() {
        ActivityAnimations1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_animations1);
        sample = (Sample) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        binding.setAnimationsSample(sample);
    }

    private void setupLayout() {
        square = (ImageView) findViewById(R.id.square_green);
        viewRoot = (ViewGroup) findViewById(R.id.sample3_root);
        findViewById(R.id.sample3_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLayout();
            }
        });
        findViewById(R.id.sample3_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePosition();
            }
        });

        findViewById(R.id.sample3_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnimationsActivity1.this, AnimationsActivity2.class);
                i.putExtra(EXTRA_SAMPLE, sample);
                transitionTo(i);
            }
        });
    }

    private void changeLayout() {
        TransitionManager.beginDelayedTransition(viewRoot);

        ViewGroup.LayoutParams params = square.getLayoutParams();
        if (sizeChanged) {
            params.width = savedWidth;
        } else {
            savedWidth = params.width;
            params.width = 200;
        }
        sizeChanged = !sizeChanged;
        square.setLayoutParams(params);
    }

    private void changePosition() {
        TransitionManager.beginDelayedTransition(viewRoot);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) square.getLayoutParams();
        if (positionChanged) {
            lp.gravity = Gravity.CENTER;
        } else {
            lp.gravity = Gravity.LEFT;
        }
        positionChanged = !positionChanged;
        square.setLayoutParams(lp);
    }



}
