package com.njcool.lzccommon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lizhichuan on 16/3/30.
 */
public class CoolMarqueeTextView extends TextView {

    public CoolMarqueeTextView(Context context) {
        super(context);
    }

    public CoolMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoolMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}
