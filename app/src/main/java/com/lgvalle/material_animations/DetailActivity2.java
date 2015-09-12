package com.lgvalle.material_animations;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;

import com.lgvalle.material_animations.databinding.ActivityDetails2Binding;

public class DetailActivity2 extends BaseDetailActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Sample sample = (Sample) getIntent().getExtras().getSerializable("sample");
        bindData(sample);
        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar();
    }

    private void bindData(Sample sample) {
        ActivityDetails2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_details2);
        binding.setDetails2Sample(sample);
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(Sample sample) {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.START);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        Detail2Fragment1 detail2Fragment1 = Detail2Fragment1.newInstance(sample);
        detail2Fragment1.setReenterTransition(slideTransition);
        detail2Fragment1.setExitTransition(slideTransition);
        detail2Fragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, detail2Fragment1)
                .commit();
    }
}
