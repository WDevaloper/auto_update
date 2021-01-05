package com.auto.update.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
import com.auto.update.view.ProgressButton;

public class UpdateType13Activity extends RootActivity {

    private Button btnAlertCancel;
    private Button btnAlertOk;
    private TextView mTvContent;
    private ProgressButton btnForceUpdate;
    private LinearLayout mLLBtnGroup;

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
            btnForceUpdate.setVisibility(View.VISIBLE);
            mLLBtnGroup.setVisibility(View.INVISIBLE);
        } else {
            btnForceUpdate.setVisibility(View.GONE);
            mLLBtnGroup.setVisibility(View.VISIBLE);
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
                finish();
            }
        });

        btnForceUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnForceUpdate.setEnabled(false);
                //右边的按钮
                download();
            }
        });

    }

    private void findView() {
        mTvContent = findViewById(R.id.mTvContent);
        btnAlertCancel = findViewById(R.id.btnAlertCancel);
        btnAlertOk = findViewById(R.id.btnAlertOk);
        btnForceUpdate = findViewById(R.id.btnForceUpdate);
        mLLBtnGroup = findViewById(R.id.mLLBtnGroup);
    }

    @Override
    public AppDownloadListener obtainDownloadListener() {
        return new AppDownloadListener() {
            @Override
            public void downloading(int progress) {
                if (downloadInfo.isForceUpdateFlag()) {
                    btnForceUpdate.setProgress(progress);
                }
            }

            @Override
            public void downloadFail(String msg) {
                btnForceUpdate.setEnabled(true);
            }

            @Override
            public void downloadComplete(String path) {
                btnForceUpdate.setEnabled(true);
                if (downloadInfo.isForceUpdateFlag()) {
                    btnForceUpdate.setProgress(100);
                }
            }

            @Override
            public void downloadStart() {
                if (!downloadInfo.isForceUpdateFlag()) {
                    Toast.makeText(UpdateType13Activity.this, ResUtils.getString(R.string.apk_file_downloading), Toast.LENGTH_SHORT).show();
                }
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
