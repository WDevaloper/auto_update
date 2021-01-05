package com.auto.update.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.update.R;
import com.auto.update.interfaces.AppDownloadListener;
import com.auto.update.model.DownloadInfo;
import com.auto.update.utils.AppUtils;
import com.auto.update.utils.LogUtils;
import com.auto.update.utils.ResUtils;
import com.auto.update.utils.RootActivity;

public class UpdateType13Activity extends RootActivity {

    private Button btnAlertCancel;
    private Button btnAlertOk;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_type13);

        findView();

        setDataAndListener();
    }

    private void setDataAndListener() {
        mTvContent.setText(downloadInfo.getUpdateLog());

        if (downloadInfo.isForceUpdateFlag()) {
        } else {
        }

        btnAlertCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //左边的按钮
                cancelTask();
                finish();
            }
        });

        btnAlertOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //右边的按钮
                download();
            }
        });
    }

    private void findView() {
        mTvContent = findViewById(R.id.mTvContent);
        btnAlertCancel = findViewById(R.id.btnAlertCancel);
        btnAlertOk = findViewById(R.id.btnAlertOk);
    }

    @Override
    public AppDownloadListener obtainDownloadListener() {
        return new AppDownloadListener() {
            @Override
            public void downloading(int progress) {
            }

            @Override
            public void downloadFail(String msg) {
            }

            @Override
            public void downloadComplete(String path) {
            }

            @Override
            public void downloadStart() {
            }

            @Override
            public void reDownload() {
            }

            @Override
            public void pause() {
            }
        };
    }

    /**
     * 启动Activity
     *
     * @param context
     * @param info
     */
    public static void launch(Context context, DownloadInfo info) {
        launchActivity(context, info, UpdateType13Activity.class);
    }

}
