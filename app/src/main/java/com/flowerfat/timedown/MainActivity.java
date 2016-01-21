package com.flowerfat.timedown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.flowerfat.timecount.TextSwitcherView;
import com.flowerfat.timecount.TimeDownMinute;
import com.flowerfat.timecount.TimeDownView;


public class MainActivity extends AppCompatActivity {

    private TextSwitcherView textSwitcherView;
    private TimeDownView timeDownView;
    private TimeDownMinute timeDownMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        initTextSwitcherView();
        initTimeDownView();
        initTimeDownMinute();


    }

    private void findView() {
        textSwitcherView = (TextSwitcherView) findViewById(R.id.textSwitcherView);
        timeDownView = (TimeDownView) findViewById(R.id.timeDownView);
        timeDownMinute = (TimeDownMinute) findViewById(R.id.timeDownMinute);
    }


    private int number = 123;

    private void initTextSwitcherView() {
        textSwitcherView.setCurrentText(number + " likes");
        number++;
    }

    private void initTimeDownView() {
        timeDownView.init(123 * 1000, 1000)
                .setOnTimeDownListener(new TimeDownView.OnTimeDownListener() {
                    @Override
                    public void onFinish() {
                        Log.i("MainActivity", "time is over");
                    }
                });
    }

    private void initTimeDownMinute() {
        timeDownMinute.init(360 * 1000);
    }


    ///////////////////////////////////////////////////// click
    public void oneClick(View v) {
        textSwitcherView.setText(number + " likes");
        number++;
    }

    public void twoClick(View v) {
        timeDownView.start();
    }

    public void threeClick(View v) {
        timeDownMinute.start();
    }

}
