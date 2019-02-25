package com.appetiser.ituneflix.pages.home;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.appetiser.ituneflix.R;

import butterknife.BindView;

public class BaseHomeActivity extends AppCompatActivity {

    /**
     * Binding views much faster than the traditional
     * find view by id method
     * Thanks to butterknife.
     */
    @BindView(R.id.home_image)
    ImageView homeImage;
    @BindView(R.id.list_image)
    ImageView listImage;
    @BindView(R.id.search_image)
    ImageView searchImage;
    @BindView(R.id.list_label)
    TextView listLabel;
    @BindView(R.id.home_label)
    TextView homeLabel;
    @BindView(R.id.search_label)
    TextView searchLabel;

}
