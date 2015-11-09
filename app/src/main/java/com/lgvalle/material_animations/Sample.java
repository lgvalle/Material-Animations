package com.lgvalle.material_animations;

import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lgvalle on 04/09/15.
 */
public class Sample implements Parcelable {

    int color;
    String name;

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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(color);
        out.writeString(name);
    }

    public static final Parcelable.Creator<Sample> CREATOR
            = new Parcelable.Creator<Sample>() {
        public Sample createFromParcel(Parcel in) {
            return new Sample(in);
        }

        public Sample[] newArray(int size) {
            return new Sample[size];
        }
    };

    private Sample(Parcel in) {
        color = in.readInt();
        name = in.readString();
    }

}
