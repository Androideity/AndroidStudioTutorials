package com.androideity.supportlibraryexample.vectordrawable;

import android.support.v4.app.Fragment;

import com.androideity.supportlibraryexample.BaseActivity;

/**
 * Created by Condesa on 26/02/16.
 */
public class VectorDrawableExampleActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return new VectorDrawableFragment();
    }
}
