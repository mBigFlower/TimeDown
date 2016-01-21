package com.flowerfat.timecount;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;

/**
 * Created by MingMing_is_a_beautiful_girl on 2016/1/21.
 *
 * the plus for TextSwitcher, you can use it easily
 * implement the it's ViewFactory instead of add two TextView when we use the TextSwitcher
 *
 * TODO user-define the textview child's textSize and textColor
 */
public class TextSwitcherView extends TextSwitcher implements TextSwitcher.ViewFactory{

    private CharSequence text;

    public TextSwitcherView(Context context) {
        super(context);
        init();
    }

    public TextSwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
//        initLayout();
        initAnim();
        setFactory(this);
    }

    private void initLayout() {
        TextView textView = new TextView(getContext());
        this.addView(textView);
        textView = new TextView(getContext());
        this.addView(textView);
    }

    private void initAnim() {
        this.setInAnimation(getContext(), R.anim.slide_in_counter);
        this.setOutAnimation(getContext(), R.anim.slide_out_counter);
    }

    @Override
    public void setText(CharSequence text) {
        super.setText(text);
        this.text = text;
    }

    @Override
    public void setCurrentText(CharSequence text) {
        super.setCurrentText(text);
        this.text = text;
    }

    public CharSequence getText(){
        return text ;
    }

    @Override
    public View makeView() {
        return new TextView(getContext());
    }
}
