package com.stellarscript.slider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

final class SliderView extends FrameLayout {

    private boolean mIsSliding;
    private final RCTEventEmitter mEventEmitter;
    private final SeekBar mSlider;
    private final SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(@NonNull final SeekBar seekBar, final int progress, final boolean fromUser) {
            final WritableMap event = Arguments.createMap();
            event.putInt(SliderEvents.ON_SLIDING_VALUE_PROP, progress);
            mEventEmitter.receiveEvent(SliderView.this.getId(), SliderEvents.ON_SLIDING_EVENT, event);
        }

        @Override
        public void onStartTrackingTouch(@NonNull final SeekBar seekBar) {
            mIsSliding = true;
        }

        @Override
        public void onStopTrackingTouch(@NonNull final SeekBar seekBar) {
            final int progress = seekBar.getProgress();
            final WritableMap event = Arguments.createMap();
            event.putInt(SliderEvents.ON_SLIDING_COMPLETE_VALUE_PROP, progress);
            mEventEmitter.receiveEvent(SliderView.this.getId(), SliderEvents.ON_SLIDING_COMPLETE_EVENT, event);
            mIsSliding = false;
        }

    };

    public SliderView(@NonNull final ThemedReactContext themedReactContext) {
        super(themedReactContext);

        mEventEmitter = themedReactContext.getJSModule(RCTEventEmitter.class);

        LayoutInflater.from(themedReactContext).inflate(R.layout.slider, SliderView.this);
        mSlider = (SeekBar) findViewById(R.id.slider);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mSlider.setOnSeekBarChangeListener(mOnSeekBarChangeListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mSlider.setOnSeekBarChangeListener(null);
    }

    @Override
    protected void onLayout(final boolean changed, final int left, final int top, final int right, final int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            layout(left, top, right, bottom);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull final MotionEvent event) {
        return mSlider.onTouchEvent(event);
    }

    public void setValue(final int value) {
        UiThreadUtil.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (!mIsSliding) {
                    mSlider.setProgress(value);
                }
            }

        });
    }

    public void setMaxValue(final int maxValue) {
        mSlider.setMax(maxValue);
    }

}
