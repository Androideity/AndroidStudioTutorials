package com.androideity.supportlibraryexample.bottomsheet;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.androideity.supportlibraryexample.BaseActivity;
import com.androideity.supportlibraryexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Condesa on 26/02/16.
 */
public class BottomSheetExampleActivity extends BaseActivity implements BottomSheetFragment.OnButtonSelectedListener {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.label_drag)
    TextView labelDrag;
    private BottomSheetAdapter adapter;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected int getResLayout() {
        return R.layout.activity_bottom_sheet_example;
    }

    @Override
    protected Fragment createFragment() {
        return new BottomSheetFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setUpRecyclerView();
    }

    @Override
    public void showDragBottomSheet() {
        bottomSheetBehavior.setPeekHeight(labelDrag.getHeight()+40);
        labelDrag.requestLayout();
    }

    @Override
    public void showDialogBottomSheet() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new CustomizedBottomSheetDialog();
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }

    @Override
    public void onBackPressed() {
        if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }

    private void setUpRecyclerView(){
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setAutoMeasureEnabled(true);

        adapter = new BottomSheetAdapter(this, getApplicationsList(this));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        FrameLayout parentThatHasBottomSheetBehavior = (FrameLayout) recyclerView.getParent().getParent();
        bottomSheetBehavior = BottomSheetBehavior.from(parentThatHasBottomSheetBehavior);
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {

                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                }
            });
        }
    }


    public List<ApplicationInfo> getApplicationsList(Context context) {
        int flags = PackageManager.GET_META_DATA;
        List<ApplicationInfo> installedApps = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> applications = pm.getInstalledApplications(flags);
        for (ApplicationInfo appInfo : applications) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
                installedApps.add(appInfo);
            }
        }
        return installedApps;
    }
}
