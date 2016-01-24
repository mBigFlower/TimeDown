# TimeDown

base of textswitcher. You can use it easyly.

## Look
![](http://7xjizl.com1.z0.glb.clouddn.com/timedown.gif)

## Usage

### TextSwitcherView

**extand the TextSwitcher**

in xml

	<com.flowerfat.timecount.TextSwitcherView
        android:id="@+id/textSwitcherView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

in java

	TextSwitcherView textSwitcherView = (TextSwitcherView) findViewById(R.id.textSwitcherView);
	textSwitcherView.setCurrentText("123 likes"); // init

	textSwitcherView.setText("aim text"); 

### TimeDownView

**TextSwitcher + CountDownTimer**
you can add the time over listener in it ;

in xml

	<com.flowerfat.timecount.TimeDownView
        android:id="@+id/timeDownView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:switcherColor="#f00"
        app:switcherSize="16sp" />

in java

	timeDownView.init(123 * 1000, 1000)
                .setOnTimeDownListener(new TimeDownView.OnTimeDownListener() {
                    @Override
                    public void onFinish() {
                        Log.i("MainActivity", "time is over");
                    }
                });
	timeDownView.start();

### TimeDownMinute

**TextSwitcher + CountDownTimer**

in xml

	<com.flowerfat.timecount.TimeDownMinute
        android:id="@+id/timeDownMinute"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:switcherColor="#ff3d943e"
        app:switcherSize="14sp" />

in java

	timeDownMinute.init(321 * 1000);
	timeDownMinute.start();

## TypedArray

    xmlns:app="http://schemas.android.com/apk/res-auto"
	<!--textColor and textSize-->
	app:switcherColor="#ff3d943e" 
    app:switcherSize="14sp"   

##