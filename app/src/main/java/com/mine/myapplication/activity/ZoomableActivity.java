package com.mine.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.mine.myapplication.R;
import com.mine.myapplication.adapter.ZoomableViewPagerAdapter;

import java.util.ArrayList;

public class ZoomableActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private static final String EXTRA_ZOOMABLE_PATHS = "extra_zoomable_paths";
    private static final String EXTRA_ZOOMABLE_INDEX = "extra_zoomable_index";

    private ViewPager mViewPager;
    private TextView mZoomableIndex;
    private ArrayList<String> mPaths;
    private int mIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoommable_activity);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getExtraData();
        initView();
        setupViewPager();
    }

    private void getExtraData() {
        mPaths = getIntent().getStringArrayListExtra(EXTRA_ZOOMABLE_PATHS);
        mIndex = getIntent().getIntExtra(EXTRA_ZOOMABLE_INDEX, 0);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mZoomableIndex = (TextView) findViewById(R.id.zoomable_index);
    }

    private void setupViewPager() {
        mViewPager.setAdapter(new ZoomableViewPagerAdapter(this, mPaths));
        mViewPager.setCurrentItem(mIndex);
        mZoomableIndex.setText(mIndex + 1 + "/" + mPaths.size());
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mZoomableIndex.setText(position + 1 + "/" + mPaths.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static void goToPage(Context context, ArrayList<String> paths, int index) {
        Intent intent = new Intent(context, ZoomableActivity.class);
        intent.putStringArrayListExtra(EXTRA_ZOOMABLE_PATHS, paths);
        intent.putExtra(EXTRA_ZOOMABLE_INDEX, index);
        context.startActivity(intent);
    }
}
