package com.lgvalle.material_animations;

import android.databinding.BindingAdapter;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lgvalle on 04/09/15.
 */
public class Sample implements Serializable {

    final int color;
    private final String name;

    public Sample(@ColorRes int color, String name) {
        this.color = color;
        this.name = name;
    }

    @BindingAdapter("bind:colorTint")
    public static void setColorTint(ImageView view, @ColorRes int color) {
        DrawableCompat.setTint(view.getDrawable(), color);
        //view.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }


}
