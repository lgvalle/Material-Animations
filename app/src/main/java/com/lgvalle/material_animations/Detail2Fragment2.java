package com.lgvalle.material_animations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Detail2Fragment2 extends Fragment {

    public static Detail2Fragment2 newInstance(Sample sample) {
        Bundle args = new Bundle();
        args.putSerializable("sample", sample);
        Detail2Fragment2 fragment = new Detail2Fragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_details2_fragment2, container, false);
        Sample sample = (Sample) getArguments().getSerializable("sample");

        ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);
        DrawableCompat.setTint(squareBlue.getDrawable(), sample.color);

        return view;
    }

}
