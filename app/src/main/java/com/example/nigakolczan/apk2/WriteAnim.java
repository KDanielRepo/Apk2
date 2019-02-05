package com.example.nigakolczan.apk2;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by NigaKolczan on 2018-09-02.
 */

public class WriteAnim extends TextView {

    private CharSequence mText;
    private int mIndex;
    private long mDelay = 500; //Default 500ms delay


    public WriteAnim(Context context) {
        super(context);
    }

    public WriteAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if(mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }
    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }
}
