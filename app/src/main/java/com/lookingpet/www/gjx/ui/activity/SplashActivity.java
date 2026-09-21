package com.lookingpet.www.gjx.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lookingpet.www.gjx.R;
import com.lookingpet.www.gjx.base.YFIDS;
import com.yufeng.ads.YFAdBuilder;
import com.yufeng.ads.splash.YFSplashAd;
import com.yufeng.listener.AdError;
import com.yufeng.listener.YFSplashAdListener;

/**
 * 开屏广告页面 (参考 SDK Demo 实现)
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private ViewGroup container;
    private boolean mGotoMainActivity = false;
    private boolean mIsPaused = false;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        container = findViewById(R.id.splash_container);
        progressBar = findViewById(R.id.progress_bar);

        // 构建广告请求配置
        YFAdBuilder splashBuilder = new YFAdBuilder.Builder()
                .setAdId(YFIDS.STH_SPLASH_ID)
                .build();

        // 加载并展示开屏广告
        new YFSplashAd(splashBuilder, this, container, new YFSplashAdListener() {
            @Override
            public void onLoaded() {
                Log.d(TAG, "广告加载成功 (onLoaded)");
            }

            @Override
            public void onExposure() {
                Log.d(TAG, "广告曝光 (onExposure)");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(AdError adError) {
                Log.e(TAG, "广告错误 (onError): " + (adError != null ? adError.getMsg() : "unknown"));
                gotoMainActivity();
            }

            @Override
            public void onClose() {
                Log.d(TAG, "广告关闭 (onClose)");
                gotoMainActivity();
            }

            @Override
            public void onClick() {
                Log.d(TAG, "广告被点击 (onClick)");
                // 点击后不立即跳转主页，等待返回
                mGotoMainActivity = false;
            }
        });
    }

    /**
     * 跳转到主页面
     */
    private void gotoMainActivity() {
        if (mIsPaused) {
            // 如果 Activity 处于后台，标记等回到前台再跳转
            mGotoMainActivity = true;
        } else {
            if (container != null) {
                container.removeAllViews();
            }
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsPaused = false;
        if (mGotoMainActivity) {
            gotoMainActivity();
        }
    }
}
