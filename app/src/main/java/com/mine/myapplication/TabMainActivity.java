package com.mine.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mine.myapplication.activity.BaseActivity;
import com.mine.myapplication.fragment.FragmentTabAdapter;
import com.mine.myapplication.fragment.ImgFragment;
import com.mine.myapplication.fragment.UserFragment;
import com.mine.myapplication.fragment.VideoFragment;
import com.mine.myapplication.utils.LogUtils;
import com.mine.myapplication.utils.SDUIUtil;
import com.mine.myapplication.utils.Utils;
import com.trello.rxlifecycle2.components.RxFragment;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by lun.zhang on 12/13/2017.
 */

public class TabMainActivity extends BaseActivity {
    @BindView(R.id.fragment_content)
    FrameLayout fragmentContent;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    ConstraintLayout container;
    private List<RxFragment> fragments = new ArrayList<RxFragment>();
    private ArrayList<String> FragmentTagList = new ArrayList<>();
    private Context context;
    private FragmentTabAdapter tabAdapter;
    public static final int IMGFRAGMENT = 0;
    public static final int VIDEORAGMENT = 1;
    public static final int USERFRAGMENT = 2;
    private int navigationHeigth = 0;
    private int fragmentHeigth = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_image:
                    tabAdapter.getRadioGroup(IMGFRAGMENT);
                    return true;
                case R.id.navigation_video:
                    tabAdapter.getRadioGroup(VIDEORAGMENT);
                    return true;
                case R.id.navigation_user:
                    tabAdapter.getRadioGroup(USERFRAGMENT);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = this;
        initView();
    }

    private void initView() {
        //获取底部导航的高度 让fragment的高度-底部导航高度获得fragment的能正常显示高度
        ViewTreeObserver vtoNavigation = navigation.getViewTreeObserver();
        vtoNavigation.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                navigation.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                navigationHeigth =  navigation.getHeight();
                navigation.getWidth();
                ConstraintLayout.LayoutParams linearParams =(ConstraintLayout.LayoutParams) fragmentContent.getLayoutParams();
                linearParams.height = fragmentHeigth - navigationHeigth;
                fragmentContent.setLayoutParams(linearParams);
            }
        });
        //获取fragment的高度
        ViewTreeObserver vtoFragment = fragmentContent.getViewTreeObserver();
        vtoFragment.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fragmentContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                fragmentHeigth =  fragmentContent.getHeight();
                fragmentContent.getWidth();
            }
        });
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTagList.add("ImgFragment");
        FragmentTagList.add("VideoFragment");
        FragmentTagList.add("UserFragment");
        fragments.add(new ImgFragment());
        fragments.add(new VideoFragment());
        fragments.add(new UserFragment());
        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.fragment_content,FragmentTagList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }
    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(tabAdapter.getCurrentTab() == IMGFRAGMENT){
            FragmentManager fm = getFragmentManager();
            ImgFragment imgFragment = (ImgFragment)fm.findFragmentByTag(FragmentTagList.get(IMGFRAGMENT));
            switch (item.getItemId()) {
                case R.id.btn_home:
                    imgFragment.pagenum = 0;
                    imgFragment.getData(imgFragment.pagenum,imgFragment.pagesize);
                    break;
                case R.id.btn_page_goto:
                    imgFragment.pagenum = (int)(0+Math.random()*imgFragment.totalpage);
                    imgFragment.getData(imgFragment.pagenum,imgFragment.pagesize);
                    break;
            }
        }


        return super.onOptionsItemSelected(item);
    }
}
