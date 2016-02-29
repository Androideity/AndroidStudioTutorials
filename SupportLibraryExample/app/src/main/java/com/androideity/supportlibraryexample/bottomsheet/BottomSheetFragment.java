package com.androideity.supportlibraryexample.bottomsheet;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androideity.supportlibraryexample.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Condesa on 26/02/16.
 */
public class BottomSheetFragment extends Fragment {

    private OnButtonSelectedListener onButtonSelectedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onButtonSelectedListener = (OnButtonSelectedListener)activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.button_show_drag_sheet)
    public void showDragBottomSheet(){
        onButtonSelectedListener.showDragBottomSheet();
    }

    @OnClick(R.id.button_show_as_dialog)
    public void showDialogBottomSheet(){
        onButtonSelectedListener.showDialogBottomSheet();
    }

    public interface OnButtonSelectedListener{
        void showDragBottomSheet();
        void showDialogBottomSheet();
    }
}
