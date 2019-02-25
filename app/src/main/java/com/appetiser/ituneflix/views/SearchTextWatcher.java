package com.appetiser.ituneflix.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class SearchTextWatcher extends IFEditText {

    public interface Listener {
        void onLoadFromDB(String text);

        void onLoadFromAPI();
    }

    public SearchTextWatcher(Context context) {
        super(context);
    }

    public SearchTextWatcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchTextWatcher(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void watcher(final Listener listener) {


        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listener.onLoadFromDB(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                listener.onLoadFromAPI();
            }
        });

    }
}
