package com.appetiser.ituneflix.views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.appetiser.ituneflix.R;

public class IFEditText extends AppCompatEditText {

    public IFEditText(Context context) {
        super(context);
        applyFont(context);
    }

    public IFEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyFont(context);
    }

    public IFEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyFont(context);
    }

    private void applyFont(Context context) {

        /**
         * This will enable us to
         * to put any font we like
         */
        if (getTypeface() != null) {
            Typeface typeface;
            if (getTypeface().getStyle() == Typeface.BOLD) {
                typeface = ResourcesCompat.getFont(context, R.font.opensans_bold);
            } else {
                typeface = ResourcesCompat.getFont(context, R.font.opensans_regular);
            }
            setTypeface(typeface);

        }

    }


}