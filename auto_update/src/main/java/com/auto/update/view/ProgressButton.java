package com.auto.update.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.auto.update.R;
import com.auto.update.utils.ResUtils;
import com.auto.update.utils.ScreenUtils;

public class ProgressButton extends View {
    // 正常
    private Paint mNormalPaint;
    private LinearGradient linearGradient;


    // 下载进度条
    private Paint mProgressPaint;
    private RectF mRectF = new RectF();

    // 文字
    private TextPaint mTextPaint;


    private String mNormalText = getContext().getString(R.string.normal_text);
    private String mProgressTextFormat = getContext().getString(R.string.progress_text);

    private State mState = State.STATE_NORMAL;
    private float mProgress = 0f;
    private float mToProgress = mProgress;
    private int mMaxProgress = 100;

    //下载平滑动画
    private ValueAnimator mProgressAnimation;

    public ProgressButton(Context context) {
        this(context, null);
    }

    public ProgressButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mNormalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setColor(Color.parseColor("#80ffffff"));
        setupAnimations();

        mTextPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(ScreenUtils.sp2px(getContext(), 15));
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setColor(Color.parseColor("#ffffff"));
    }

    private void setupAnimations() {
        //ProgressBar的动画
        mProgressAnimation = ValueAnimator.ofFloat(0f, 1f).setDuration(300);
        mProgressAnimation.addUpdateListener(animation -> {
            float timePercent = (float) animation.getAnimatedValue();
            mProgress += (mToProgress - mProgress) * timePercent;
            invalidate();
        });
    }

    public LinearGradient getLinearGradient() {
        if (linearGradient != null) {
            return linearGradient;
        }
        linearGradient = new LinearGradient(0f, 0f,
                getMeasuredWidth(),
                getMeasuredHeight(),
                new int[]{ResUtils.getColor(R.color.color_FF9900), ResUtils.getColor(R.color.color_FF3333)},
                null,
                Shader.TileMode.CLAMP
        );
        return linearGradient;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawText(canvas);
    }

    private void drawBackground(Canvas canvas) {
        mNormalPaint.setShader(getLinearGradient());
        canvas.drawRect(new RectF(0f, 0f, getWidth(), getHeight()), mNormalPaint);

        if (mState == State.STATE_DOWNLOADING) {
            float left = getWidth() * (mProgress / mMaxProgress);
            mRectF.set(left, 0f, getWidth(), getHeight());
            canvas.drawRect(mRectF, mProgressPaint);
        }
    }

    public void setProgress(int progress) {
        mToProgress = progress;

        if (mToProgress == 100f) mState = State.STATE_NORMAL;
        else mState = State.STATE_DOWNLOADING;

        if (!mProgressAnimation.isRunning()) {
            mProgressAnimation.start();
            return;
        }
        mProgressAnimation.resume();
        mProgressAnimation.start();
    }


    private void drawText(Canvas canvas) {
        //计算Baseline绘制的Y坐标
        float y = canvas.getHeight() / 2f - (mTextPaint.descent() / 2 + mTextPaint.ascent() / 2);

        if (mState == State.STATE_DOWNLOADING) {//下载中(10%)
            String text = String.format(mProgressTextFormat, (int) mProgress);
            float textWidth = mTextPaint.measureText(text);
            canvas.drawText(
                    text, (getMeasuredWidth() - textWidth) / 2, y, mTextPaint
            );
            return;
        }

        // 立即更新
        float textWidth = mTextPaint.measureText(mNormalText);
        mTextPaint.setShader(null);
        canvas.drawText(
                mNormalText, (getMeasuredWidth() - textWidth) / 2, y, mTextPaint
        );
    }
}
