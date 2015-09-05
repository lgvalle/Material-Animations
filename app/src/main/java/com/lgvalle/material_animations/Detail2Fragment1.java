package com.lgvalle.material_animations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by lgvalle on 05/09/15.
 */
public class Detail2Fragment1 extends Fragment {

    public static Detail2Fragment1 newInstance(Sample sample) {

        Bundle args = new Bundle();

        args.putSerializable("sample", sample);
        Detail2Fragment1 fragment = new Detail2Fragment1();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details2_fragment1, container, false);
        final Sample sample = (Sample) getArguments().getSerializable("sample");

        final ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.color);

        view.findViewById(R.id.sample2_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Sample 2 Fragment 2
                addNextFragment(sample, squareBlue, false);
            }
        });

        view.findViewById(R.id.sample2_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Sample 2 Fragment 2
                addNextFragment(sample, squareBlue, true);
            }
        });

        return view;
    }

    private void addNextFragment(Sample sample, ImageView squareBlue, boolean overlap) {
        Detail2Fragment2 detail2Fragment2 = Detail2Fragment2.newInstance(sample);

        Slide slideTransition = new Slide(Gravity.END);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        ChangeBounds changeBoundsTransition = new ChangeBounds();
        changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

        detail2Fragment2.setEnterTransition(slideTransition);
        detail2Fragment2.setAllowEnterTransitionOverlap(overlap);
        detail2Fragment2.setAllowReturnTransitionOverlap(overlap);
        detail2Fragment2.setSharedElementEnterTransition(changeBoundsTransition);

        getFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, detail2Fragment2)
                .addToBackStack(null)
                .addSharedElement(squareBlue, getString(R.string.square_blue_name))
                .commit();
    }

}
