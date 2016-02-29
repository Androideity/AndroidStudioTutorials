package com.androideity.supportlibraryexample.bottomsheet;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androideity.supportlibraryexample.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Condesa on 26/02/16.
 */
public class BottomSheetAdapter extends RecyclerView.Adapter<BottomSheetAdapter.ViewHolder> {

    private PackageManager mPackageManager;
    private Context mContext;
    private List<ApplicationInfo> applications;

    public BottomSheetAdapter(Context context, List<ApplicationInfo> applications) {
        this.applications = applications;
        mContext = context;
        mPackageManager = mContext.getPackageManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_recycler_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ApplicationInfo applicationInfo = applications.get(position);
        holder.labelTitle.setText(applicationInfo.loadLabel(mPackageManager));
        holder.imageIcon.setImageDrawable(applicationInfo.loadIcon(mPackageManager));
    }

    @Override
    public int getItemCount() {
        return applications == null ? 0 : applications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_icon)
        ImageView imageIcon;
        @Bind(R.id.label_title)
        TextView labelTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
