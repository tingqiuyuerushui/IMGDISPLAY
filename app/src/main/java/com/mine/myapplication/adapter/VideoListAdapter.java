package com.mine.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.gson.annotations.Until;
import com.mine.myapplication.R;
import com.mine.myapplication.entity.VideoListEntity;
import com.mine.myapplication.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by lun.zhang on 12/15/2017.
 */

public class VideoListAdapter extends BaseAdapter {
    private ArrayList<VideoListEntity.ContentBean> videoList;
    private ArrayList<String> thumbList;
    Context context;
    public VideoListAdapter(Context context,ArrayList<VideoListEntity.ContentBean> videoList,ArrayList<String> thumbList) {
        this.context = context;
        this.videoList = videoList;
        this.thumbList = thumbList;
    }
    @Override
    public int getCount() {
        return videoList == null ? 0 : videoList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (null == convertView) {
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_videoview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(videoList.size() > 0){
            viewHolder.videoplayer.setUp(
                    videoList.get(position).getUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    videoList.get(position).getTitle_text());
//            viewHolder.videoplayer.thumbImageView.setImageBitmap(bmpList.get(position));
            Picasso.with(convertView.getContext())
                    .load(thumbList.get(position))
                    .into(viewHolder.videoplayer.thumbImageView);
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.videoplayer)
        JCVideoPlayerStandard videoplayer;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
