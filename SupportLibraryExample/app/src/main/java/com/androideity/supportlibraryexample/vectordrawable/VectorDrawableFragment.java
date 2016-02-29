package com.androideity.supportlibraryexample.vectordrawable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androideity.supportlibraryexample.R;

import butterknife.ButterKnife;

/**
 * Created by Condesa on 26/02/16.
 */
public class VectorDrawableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vector_drawable, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
