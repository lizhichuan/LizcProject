package com.njcool.lzccommon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by chuan on 2017/8/25.
 */

public class CoolLastInputEditText extends EditText {

    public CoolLastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CoolLastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoolLastInputEditText(Context context) {
        super(context);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后面
        if (selStart == selEnd) {//防止不能多选
            setSelection(getText().length());
        }

    }
}
