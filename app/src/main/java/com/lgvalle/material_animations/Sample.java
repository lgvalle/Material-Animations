package com.lgvalle.material_animations;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lgvalle on 04/09/15.
 */
public class Sample implements Serializable {

    int color;
    String name;

    public Sample(int color, String name) {
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

    public int getColor() {
        return color;
    }


}
