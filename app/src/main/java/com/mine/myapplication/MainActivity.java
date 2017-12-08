package com.mine.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;
import com.mine.myapplication.activity.BaseActivity;
import com.mine.myapplication.activity.ZoomableActivity;
import com.mine.myapplication.adapter.ImgListAdapter;
import com.mine.myapplication.entity.ImgListPageEntity;
import com.mine.myapplication.myInterface.MyItemOnClickListener;
import com.mine.myapplication.net.DefaultObserver;
import com.mine.myapplication.net.IdeaApi;
import com.mine.myapplication.net.MyBasicResponse;
import com.mine.myapplication.special.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefresh_layout)
    SwipeRefreshLayout swiperefreshLayout;
    private StaggeredGridLayoutManager mStaggerdGridLayoutManager;
    private ImgListPageEntity imgListEntity = null;
    private ImgListAdapter imgListAdapter = null;
    private Context context;
    private ImgListPageEntity.ListImgPageInfoBean results = null;
    private ArrayList<String> urlArray;
    private List<ImgListPageEntity.ListImgPageInfoBean.ContentBean> imgListUrl;
    private int pagenum = 0;
    private int pagesize = 12;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        context = this;
        initRecyclerView();
        initView();

    }

    private void initView() {
        getData(pagenum,pagesize);
        swiperefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(pagenum,pagesize);
                swiperefreshLayout.setRefreshing(false);
            }
        });
    }

    private void initRecyclerView(){
        mStaggerdGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mStaggerdGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(mStaggerdGridLayoutManager);
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                mStaggerdGridLayoutManager.invalidateSpanAssignments();
//            }
//        });
        SpacesItemDecoration decoration=new SpacesItemDecoration(2);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setHasFixedSize(true);
    }
    public void getData(final int page, int pagesize) {
        IdeaApi.getApiService()
                .getImgListPage(page,pagesize)
                .compose(this.<MyBasicResponse<ImgListPageEntity.ListImgPageInfoBean>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<MyBasicResponse<ImgListPageEntity.ListImgPageInfoBean>>(this) {
                    @Override
                    public void onSuccess(MyBasicResponse<ImgListPageEntity.ListImgPageInfoBean> response) {
                        if(imgListUrl == null){
                            imgListUrl = new ArrayList<>();
                        }else{
                            imgListUrl.clear();
                        }
                        urlArray = new ArrayList<>();
                        for (int i = 0; i < response.getListImgPageInfo().getContent().size(); i++) {
                            imgListUrl.add(response.getListImgPageInfo().getContent().get(i));
                            urlArray.add(response.getListImgPageInfo().getContent().get(i).getImg_url());
                        }

                        pagenum ++;
                        if (imgListAdapter == null){
                            imgListAdapter = new ImgListAdapter(context,imgListUrl);
                            recyclerView.setAdapter(imgListAdapter);
                            imgListAdapter.getRandomHeight(imgListUrl);
                            imgListAdapter.setItemOnClickListener(new MyItemOnClickListener() {
                                @Override
                                public void onItemOnClick(View view, int postion) {
                                    Toast.makeText(context,postion+"",Toast.LENGTH_SHORT).show();
                                    ZoomableActivity.goToPage(context, urlArray, postion);
                                }
                            });
                        }else{
                            imgListAdapter.getRandomHeight(imgListUrl);
                            imgListAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
