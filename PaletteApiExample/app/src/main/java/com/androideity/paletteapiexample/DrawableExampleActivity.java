package com.androideity.paletteapiexample;

import android.os.Bundle;

/**
 * Created by Condesa on 24/02/16.
 */
public class DrawableExampleActivity extends BaseActivity{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_drawable_example;
    }

    @Override
    protected int getCollapsingToolbarTitle() {
        return R.string.title_drawable_example;
    }

    @Override
    protected boolean showUpButton() {
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpParallaxToolbarColor();
    }
}
