package com.lgvalle.material_animations;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lgvalle on 04/09/15.
 */
public class Sample {

    int color;
    String name;
    Drawable icon;
    View.OnClickListener onClickListener;

    public Sample(Drawable icon, int color, String name) {
        this.icon = icon;
        this.color = color;
        this.name = name;
    }

    @BindingAdapter("bind:colorFilter")
    public static void setColorFilter(ImageView view, int color) {
        view.setColorFilter(color);
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
