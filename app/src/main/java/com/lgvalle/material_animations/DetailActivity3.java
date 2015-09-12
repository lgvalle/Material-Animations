package com.lgvalle.material_animations;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lgvalle.material_animations.databinding.ActivityDetails3Binding;

public class DetailActivity3 extends BaseDetailActivity {

    private ImageView square;
    private ViewGroup viewRoot;
    private boolean sizeChanged;
    private int savedWidth;
    private boolean positionChanged;
    private boolean colorChanged;
    private Sample sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindData();
        setupLayout();
        setupToolbar();
    }

    private void bindData() {
        ActivityDetails3Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_details3);
        sample = (Sample) getIntent().getExtras().getSerializable("sample");
        binding.setDetails3Sample(sample);
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
                changeColor();
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
            lp.gravity = Gravity.START;
        }
        positionChanged = !positionChanged;
        square.setLayoutParams(lp);
    }

    private void changeColor() {
        TransitionManager.beginDelayedTransition(viewRoot);

        if (colorChanged) {
            DrawableCompat.setTint(square.getDrawable(), sample.color);
        } else {
            DrawableCompat.setTint(square.getDrawable(), Color.RED);
        }
        colorChanged = !colorChanged;
    }


}
