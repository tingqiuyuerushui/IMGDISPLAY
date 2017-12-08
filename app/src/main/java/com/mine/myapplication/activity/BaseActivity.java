package com.mine.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.QualityInfo;
import com.mine.myapplication.utils.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;


/**
 * Created by lun.zhang on 12/6/2017.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        /**
//         * 设置渐进式JPEG Config
//         * */
//        ProgressiveJpegConfig config = new ProgressiveJpegConfig() {
//            @Override
//            public int getNextScanNumberToDecode(int scanNumber) {
//                return 0;
//            }
//
//            @Override
//            public QualityInfo getQualityInfo(int scanNumber) {
//                return null;
//            }
//        };
//        /**
//         * 直接控制ImagePipeline Config
//         * */
//        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
//                .setProgressiveJpegConfig(config)
//                .setDownsampleEnabled(true)
//                .build();
        Fresco.initialize(this);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
