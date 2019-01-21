package com.blueshroom.harry.avoid;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

/**
 * Credit: Sylvain Saurel
 * https://medium.com/@ssaurel/create-a-type-writer-effect-on-android-4dc8f2ccada6
 */

public class TypeWriter extends android.support.v7.widget.AppCompatTextView
{
    private CharSequence mText;
    private int mIndex;
    private long mDelay = 150; // in ms
    public TypeWriter(Context context) {
        super(context);
    }
    public TypeWriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));

            if(mIndex <= mText.length())
            {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence txt)
    {
        mText = txt;
        mIndex = 0;
        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long m) {
        mDelay = m;
    }
}