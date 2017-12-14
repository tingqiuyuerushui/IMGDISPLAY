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
import android.widget.FrameLayout;

import com.mine.myapplication.activity.BaseActivity;
import com.mine.myapplication.fragment.FragmentTabAdapter;
import com.mine.myapplication.fragment.ImgFragment;
import com.mine.myapplication.fragment.UserFragment;
import com.mine.myapplication.fragment.VideoFragment;
import com.trello.rxlifecycle2.components.RxFragment;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

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
