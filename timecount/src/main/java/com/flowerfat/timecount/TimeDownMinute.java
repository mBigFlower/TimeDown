package com.flowerfat.timecount;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by bigflower on 2016/1/21.
 *
 */
public class TimeDownMinute extends LinearLayout {

    private TimeCount timeCount;

    private TextSwitcherView timeDownSecond;
    private TextSwitcherView timeDownMinute;

    private int minuteCount, secondCount;
    private CharSequence timeOverStr;
    private int textColor = Color.BLACK;
    private int textSize = 16;

    public TimeDownMinute(Context context) {
        super(context);
        initWidget(context);
    }

    public TimeDownMinute(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.TimeDown, 0, 0);
        try {
            textColor = a.getColor(R.styleable.TimeDown_switcherColor, Color.BLACK);
            textSize = a.getDimensionPixelSize(R.styleable.TimeDown_switcherSize, 16);
        } finally {
            a.recycle();
        }

        initWidget(context);
    }


    public TimeDownMinute(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWidget(context);
    }
    ///////////////////////////////////////////////
    // init
    ///////////////////////////////////////////////
    private void initWidget(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.time_down_minute, this, true);
        timeDownMinute = (TextSwitcherView) v.findViewById(R.id.minuteTsv);
        timeDownSecond = (TextSwitcherView) v.findViewById(R.id.secondTsv);
        initMinute();
        initMidContent(v);
        initSecond();
    }
    private void initMinute(){
        timeDownMinute.setTextSize(textSize);
        timeDownMinute.setTextColor(textColor);
    }
    private void initMidContent(View v){
        TextView midTextView = (TextView) v.findViewById(R.id.midTv);
        midTextView.setTextSize(textSize);
        midTextView.setTextColor(textColor);
    }
    private void initSecond(){
        timeDownSecond.setTextSize(textSize);
        timeDownSecond.setTextColor(textColor);
    }


    ///////////////////////////////////////////////
    // outside use
    ///////////////////////////////////////////////
    public TimeDownMinute init(long millisInFuture) {
        return init(millisInFuture, "0");
    }

    public TimeDownMinute init(long millisInFuture, CharSequence timeOverStr) {
        if (millisInFuture > 3600000)
            throw new IndexOutOfBoundsException("you can only use a time below 60m(3600s)");
        timeCount = new TimeCount(millisInFuture, 1000);
        int allSeconds = (int) millisInFuture / 1000;
        secondCount = allSeconds % 60;
        minuteCount = allSeconds / 60;
        timeDownMinute.setCurrentText(number2Full(minuteCount));
        timeDownSecond.setCurrentText(number2Full(secondCount));
        this.timeOverStr = timeOverStr;
        return this;
    }

    public void start() {
        timeCount.start();
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
        int secondCount = (int) second % 60;
        int minuteCount = (int) second / 60;
        if (this.secondCount != secondCount) {
            this.secondCount = secondCount;
            timeDownSecond.setText(number2Full(secondCount));
        }
        if (this.minuteCount != minuteCount) {
            this.minuteCount = minuteCount;
            timeDownMinute.setText(number2Full(minuteCount));
        }
    }

    /**
     * make the time beauty
     *
     * @return
     */
    private String number2Full(int time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return "" + time;
        }
    }

}
