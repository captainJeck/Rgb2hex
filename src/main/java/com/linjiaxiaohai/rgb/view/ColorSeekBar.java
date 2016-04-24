package com.linjiaxiaohai.rgb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.linjiaxiaohai.rgb.R;

/**
 * Created by mengxn on 15-9-25.
 */
public class ColorSeekBar extends LinearLayout {

    private TextView mHint;
    private SeekBar mProgress;
    private TextView mProgressText;

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;

    public ColorSeekBar(Context context) {
        super(context);
    }

    public ColorSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mHint = (TextView) findViewById(R.id.text);
        mProgress = (SeekBar) findViewById(R.id.progress);
        mProgressText = (TextView) findViewById(R.id.progress_text);

        mProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar bar, int i, boolean b) {
                mProgressText.setText(String.valueOf(i));
                if (getOnSeekBarChangeListener() != null) {
                    getOnSeekBarChangeListener().onProgressChanged(bar, i, b);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar bar) {
                if (getOnSeekBarChangeListener() != null) {
                    getOnSeekBarChangeListener().onStartTrackingTouch(bar);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar bar) {
                if (getOnSeekBarChangeListener() != null) {
                    getOnSeekBarChangeListener().onStopTrackingTouch(bar);
                }
            }
        });

    }

    public void setHint(String hint) {
        mHint.setText(hint);
    }

    public int getProgress() {
        return mProgress.getProgress();
    }

    public void setProgress(int progress) {
        mProgress.setProgress(progress);
    }

    public void setMax(int max) {
        mProgress.setMax(max);
    }

    public SeekBar.OnSeekBarChangeListener getOnSeekBarChangeListener() {
        return onSeekBarChangeListener;
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.onSeekBarChangeListener = onSeekBarChangeListener;
    }
}
