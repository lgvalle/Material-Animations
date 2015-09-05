package com.lgvalle.material_animations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;

import com.lgvalle.material_animations.databinding.ActivityDetails1Binding;

public class DetailActivity1 extends AppCompatActivity {

    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetails1Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_details1);
        sample = (Sample) getIntent().getExtras().getSerializable("sample");
        binding.setDetails1Sample(sample);
        setupLayout();
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    private void setupLayout() {
        findViewById(R.id.sample1_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Visibility returnTransition = buildReturnTransition();
                getWindow().setReturnTransition(returnTransition);

                finishAfterTransition();
            }
        });

        findViewById(R.id.sample1_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * If no return transition is defined Android will use a reversed enter transition
                 * In this case, return transition will be a reversed Slide (defined in buildEnterTransition)
                 */
                finishAfterTransition();
            }
        });
    }

    private Visibility buildEnterTransition() {
        int animDuration = getResources().getInteger(R.integer.anim_duration_long);

        Slide enterTransition = new Slide();
        enterTransition.setSlideEdge(Gravity.END);
        enterTransition.setDuration(animDuration);
        // This view will not be affected by enter transition animation
        enterTransition.excludeTarget(R.id.sample1_title, true);

        return enterTransition;
    }

    private Visibility buildReturnTransition() {
        int animDuration = getResources().getInteger(R.integer.anim_duration_long);

        Visibility enterTransition = new Fade();
        enterTransition.setDuration(animDuration);
        return enterTransition;
    }
}
