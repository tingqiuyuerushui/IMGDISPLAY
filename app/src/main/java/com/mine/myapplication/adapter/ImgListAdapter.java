package com.mine.myapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.mine.myapplication.R;
import com.mine.myapplication.entity.ImgListPageEntity;
import com.mine.myapplication.myInterface.MyItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lun.zhang on 12/6/2017.
 */

public class ImgListAdapter extends RecyclerView.Adapter<ImgListAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<ImgListPageEntity.ListImgPageInfoBean.ContentBean> listDate;
    private MyItemOnClickListener mMyItemOnClickListener;
    private List<Integer> mHeights;
    public ImgListAdapter(Context context, List<ImgListPageEntity.ListImgPageInfoBean.ContentBean> listDate) {
        mInflater = LayoutInflater.from(context);
        this.listDate = listDate;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        ViewGroup.LayoutParams layoutParams = holder.mainSimpleDraweeView.getLayoutParams();
//        layoutParams.height = mHeights.get(position);
//        holder.itemView.setLayoutParams(layoutParams);
        if (listDate!=null){
            Uri uri =  Uri.parse(listDate.get(position).getImg_url());
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setAutoPlayAnimations(true)
                    .setOldController(holder.mainSimpleDraweeView.getController())
                    .build();
            holder.mainSimpleDraweeView.setController(controller);
//            DraweeController controller = Fresco.newDraweeControllerBuilder()
//                    .setUri(uri)
//                    .setAutoPlayAnimations(true)
//                    .setOldController(holder.mainSimpleDraweeView.getController())
//                    .build();
//            holder.mainSimpleDraweeView.setController(controller);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        SimpleDraweeView mainSimpleDraweeView;
        MyItemOnClickListener mListener;
        public ViewHolder(View itemView,MyItemOnClickListener myItemOnClickListener) {
            super(itemView);
            this.mListener = myItemOnClickListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(mListener!=null){
                mListener.onItemOnClick(view,getPosition());
            }
        }
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recyclerview_item,viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view,mMyItemOnClickListener);
        viewHolder.mainSimpleDraweeView = view
                .findViewById(R.id.main_simple_drawee_view);
        return viewHolder;
    }
    public void getRandomHeight(List<ImgListPageEntity.ListImgPageInfoBean.ContentBean> mList){
        mHeights = new ArrayList<>();
        for(int i=0; i < mList.size();i++){
            //随机的获取一个范围为300-700直接的高度
            mHeights.add((int)(300+Math.random()*400));
        }
    }
    @Override
    public int getItemCount() {
        return listDate == null ? 0 : listDate.size();
    }
    public void setItemOnClickListener(MyItemOnClickListener listener){
        this.mMyItemOnClickListener = listener;
    }
}
