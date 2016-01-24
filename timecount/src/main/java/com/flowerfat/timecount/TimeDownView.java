package com.flowerfat.timecount;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by bigflower on 2016/1/21.
 */
public class TimeDownView extends TextSwitcherView {

    private OnTimeDownListener mInterface;

    private boolean isStart;

    private TimeCount timeCount;
    private CharSequence timeOverStr;

    public TimeDownView(Context context) {
        super(context);
    }

    public TimeDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimeDownView init(long millisInFuture, long countDownInterval) {
        return init(millisInFuture, countDownInterval, "0");
    }

    public TimeDownView init(long millisInFuture, long countDownInterval, CharSequence timeOverStr) {
        setCurrentText(millisInFuture / 1000 + "");
        timeCount = new TimeCount(millisInFuture, countDownInterval);
        this.timeOverStr = timeOverStr;
        return this;
    }

    public void start() {
        if (isStart) {
            timeCount.cancel();
            timeCount.start();
        } else {
            isStart = true ;
            timeCount.start();
        }
    }

    public void cancel() {
        timeCount.cancel();
    }

    public boolean isStart() {
        return isStart;
    }

    ///////////////////////////////////////////////
    //
    ///////////////////////////////////////////////

    class TimeCount extends CountDownTimer {
        // total time and the interval time
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            isStart = false;
            setText(timeOverStr);
            if (mInterface != null) {
                mInterface.onFinish();
            }
        }

        // the count is doing
        // make the millisUntilFinished divide 1 is to make sure it is not equal with initTime ;
        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("onTick", "" + millisUntilFinished);
            couting((millisUntilFinished - 1) / 1000);
        }
    }

    private void couting(long second) {
        setText(second + "");
    }

    ///////////////////////////////////////////////
    // interface
    ///////////////////////////////////////////////
    public TimeDownView setOnTimeDownListener(OnTimeDownListener listener) {
        this.mInterface = listener;
        return this;
    }

    public interface OnTimeDownListener {
        void onFinish();
    }

}
