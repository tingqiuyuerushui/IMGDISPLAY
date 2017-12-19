package com.mine.myapplication.fragment;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mine.myapplication.R;
import com.mine.myapplication.adapter.VideoListAdapter;
import com.mine.myapplication.customview.MyListView;
import com.mine.myapplication.entity.VideoListEntity;
import com.mine.myapplication.net.DefaultObserver;
import com.mine.myapplication.net.IdeaApi;
import com.mine.myapplication.net.MyBasicResponse;
import com.mine.myapplication.utils.SDUIUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by lun.zhang on 12/13/2017.
 */

public class VideoFragment extends BaseFragment {
    public int pagenum = 0;
    public int pagesize = 4;
    public int totalpage = 12;
    @BindView(R.id.list_video)
    MyListView listVideo;
    @BindView(R.id.swiperefresh_layout)
    SwipeRefreshLayout swiperefreshLayout;
    Unbinder unbinder;
    private Context context;
    private ArrayList<VideoListEntity.ContentBean> videoList;
    private ArrayList<String> thumbList;
    private VideoListAdapter videoListAdapter = null;
    SensorManager sensorManager;
    JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = getActivity();
//        ViewGroup.LayoutParams params= (ViewGroup.LayoutParams) listVideo.getLayoutParams();
//        params.height= SDUIUtil.getScreenHeight(context)-400;//设置当前控件布局的高度
//        listVideo.setLayoutParams(params);
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
        getData(pagenum, pagesize);
        swiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(pagenum < totalpage){
                    getData(pagenum, pagesize);
                    swiperefreshLayout.setRefreshing(false);
                }else{
                    swiperefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    public void getData(final int page, int pagesize) {
        IdeaApi.getApiService()
                .getVideoListPage(page, pagesize)
                .compose(this.<MyBasicResponse<VideoListEntity>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<MyBasicResponse<VideoListEntity>>(getActivity()) {
                    @Override
                    public void onSuccess(MyBasicResponse<VideoListEntity> response) {
                        if (videoList == null) {
                            videoList = new ArrayList<>();
                        } else {
                            videoList.clear();
                        }
                        if (thumbList == null) {
                            thumbList = new ArrayList<>();
                        } else {
                            thumbList.clear();
                        }
                        for (int i = 0; i < response.getResult().getContent().size(); i++) {
                            videoList.add(response.getResult().getContent().get(i));
                            thumbList.add(response.getResult().getContent().get(i).getThumb_url());
                        }
                        totalpage = response.getResult().getTotalPages();
                        pagenum++;
                        if (videoListAdapter == null) {
                            videoListAdapter = new VideoListAdapter(context, videoList, thumbList);
                            listVideo.setAdapter(videoListAdapter);
                        } else {
                            videoListAdapter.notifyDataSetChanged();
                        }
//                        setListViewHeightBasedOnChildren(listVideo);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //使用FragmentManager的show hide方法来显示和隐藏fragment的时候使用
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden && sensorManager != null) {
            sensorManager.unregisterListener(sensorEventListener);
            JCVideoPlayer.releaseAllVideos();
        } else {
            if(pagenum < totalpage){

                getData(pagenum, pagesize);
            }
        }
    }
    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (dp * scale + 0.5f);
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {

        //获取listview的适配器
        ListAdapter listAdapter = listView.getAdapter(); //item的高度

        if (listAdapter == null) {

            return;
        }
        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = videoListAdapter.getView(i, null, listView);

            listItem.measure(0, 0); //计算子项View 的宽高 //统计所有子项的总高度
            totalHeight += Dp2Px(context,listItem.getMeasuredHeight())+listView.getDividerHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight; listView.setLayoutParams(params);

    }

}
