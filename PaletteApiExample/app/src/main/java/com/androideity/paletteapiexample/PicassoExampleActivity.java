package com.androideity.paletteapiexample;

import com.androideity.paletteapiexample.picasso.PaletteTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Condesa on 24/02/16.
 */
public class PicassoExampleActivity extends BaseActivity{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_picasso_example;
    }

    @Override
    protected boolean showUpButton() {
        return true;
    }

    @Override
    protected int getCollapsingToolbarTitle() {
        return R.string.title_picasso_example;
    }

    @Override
    protected int getStringContent() {
        return R.string.label_content_picasso;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Picasso.with(this)
                .load("https://dl.dropboxusercontent.com/u/12316307/androideity/steins_gate.png")
                .fit().centerCrop()
                .transform(PaletteTransformation.instance())
                .into(imageViewToolbar, new Callback.EmptyCallback() {
                    @Override public void onSuccess() {
                                        setUpParallaxToolbarColor();
                    }
                });
    }
}
