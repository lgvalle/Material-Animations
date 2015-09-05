package com.lgvalle.material_animations;

import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

public class DetailActivity1 extends BaseDetailSampleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupLayout();
        setupWindowAnimations();
    }

    @Override
    int getContentView() {
        return R.layout.activity_details1;
    }

    private void setupWindowAnimations() {
        Visibility enterTransition = buildEnterTransition();
        getWindow().setEnterTransition(enterTransition);
    }

    private void setupLayout() {
        ImageView sampleIcon = (ImageView) findViewById(R.id.toolbar_sample_icon);
        sampleIcon.setColorFilter(sample.color);

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
