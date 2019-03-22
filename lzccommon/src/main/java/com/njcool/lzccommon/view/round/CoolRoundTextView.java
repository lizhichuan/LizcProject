package com.njcool.lzccommon.view.round;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by chuan on 2017/7/18.
 */

/**
 * 用于需要圆角矩形框背景的TextView的情况,减少直接使用TextView时引入的shape资源文件
 */
public class CoolRoundTextView extends TextView {
    private CoolRoundViewDelegate delegate;

    public CoolRoundTextView(Context context) {
        this(context, null);
    }

    public CoolRoundTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoolRoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new CoolRoundViewDelegate(this, context, attrs);
    }

    /**
     * use delegate to set attr
     */
    public CoolRoundViewDelegate getDelegate() {
        return delegate;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (delegate.isWidthHeightEqual() && getWidth() > 0 && getHeight() > 0) {
            int max = Math.max(getWidth(), getHeight());
            int measureSpec = MeasureSpec.makeMeasureSpec(max, MeasureSpec.EXACTLY);
            super.onMeasure(measureSpec, measureSpec);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (delegate.isRadiusHalfHeight()) {
            delegate.setCornerRadius(getHeight() / 2);
        } else {
            delegate.setBgSelector();
        }
    }
}
