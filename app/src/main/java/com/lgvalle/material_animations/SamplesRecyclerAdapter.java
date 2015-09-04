package com.lgvalle.material_animations;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgvalle.material_animations.databinding.RowSampleBinding;

import java.util.List;

public class SamplesRecyclerAdapter extends RecyclerView.Adapter<SamplesRecyclerAdapter.SamplesViewHolder> {
    private final Activity activity;
    private List<Sample> samples;

    public SamplesRecyclerAdapter(Activity activity, List<Sample> samples) {
        this.activity = activity;
        this.samples = samples;
    }

    @Override
    public SamplesViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        RowSampleBinding binding = RowSampleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SamplesViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final SamplesViewHolder viewHolder, final int position) {
        viewHolder.binding.setSample(samples.get(position));
        viewHolder.binding.sampleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position) {
                    case 0:
                        transitionToActivity(DetailActivity1.class);
                        break;
                    case 1:
                        transitionToActivity(DetailActivity2.class, viewHolder, R.string.square_blue_name);
                        break;

                    case 2:
                        /*
                        TransitionManager.beginDelayedTransition(sceneRoot);
                        setViewWidth(squareRed, 500);
                        setViewWidth(squareBlue, 500);
                        setViewWidth(squareGreen, 500);
                        setViewWidth(squareOrange, 500);
                        */
                        break;

                    case 3:
                        transitionToActivity(DetailActivity3.class, viewHolder, R.string.square_orange_name);
                        break;
                }
            }
        });
    }

    private void transitionToActivity(Class target) {
        Intent i = new Intent(activity, target);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity);
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

    private void transitionToActivity(Class target, SamplesViewHolder viewHolder, int transitionName) {
        Intent i = new Intent(activity, target);
        View sharedView = viewHolder.binding.sampleIcon;
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedView, activity.getString(transitionName));
        activity.startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override
    public int getItemCount() {
        return samples.size();
    }


    public class SamplesViewHolder extends RecyclerView.ViewHolder {
        RowSampleBinding binding;

        public SamplesViewHolder(View rootView) {
            super(rootView);
            binding = DataBindingUtil.bind(rootView);

        }
    }
}
