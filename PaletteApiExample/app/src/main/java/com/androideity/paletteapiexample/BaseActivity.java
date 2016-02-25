package com.androideity.paletteapiexample;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.androideity.paletteapiexample.widgets.SquareImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Condesa on 24/02/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.image_view_toolbar)
    protected SquareImageView imageViewToolbar;
    @Bind(R.id.floating_action_button)
    protected FloatingActionButton floatingActionButton;
    @Bind(R.id.label_content)
    protected TextView labelContent;

    protected int getLayoutResId(){
        return R.layout.activity_drawable_example;
    }

    protected boolean showUpButton(){
        return false;
    }

    protected int getCollapsingToolbarTitle(){
        return R.string.app_name;
    }

    protected int getStringContent(){
        return R.string.label_content_drawable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);

        if(toolbar != null){
            setSupportActionBar(toolbar);
            if(showUpButton()){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            collapsingToolbarLayout.setTitle(getResources().getString(getCollapsingToolbarTitle()));
        }
        labelContent.setText(getStringContent());
    }

    protected void setUpParallaxToolbarColor(){
        Bitmap bitmap = ((BitmapDrawable) imageViewToolbar.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
                int primary = getResources().getColor(R.color.colorPrimary);
                int accent = getResources().getColor(R.color.colorAccent);


                collapsingToolbarLayout.setContentScrimColor(palette.getDarkMutedColor(primary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkVibrantColor(primaryDark));
                floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(palette.getVibrantColor(accent)));

            }
        });
    }

    @OnClick(R.id.floating_action_button)
    public void share(){

    }
}
