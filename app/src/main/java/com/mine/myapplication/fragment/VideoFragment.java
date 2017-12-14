package com.mine.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mine.myapplication.R;

/**
 * Created by lun.zhang on 12/13/2017.
 */

public class VideoFragment extends BaseFragment {
    private View view;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
