package com.njcool.lzccommon.common;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.njcool.lzccommon.R;


/**
 * Created by chuan on 2017/7/4.
 */

public class CoolCommonPop {

    public CoolCommonPop() {
    }

    private PopupWindow pop;

    private PopClickListener popClickListener;

    public PopClickListener getPopClickListener() {
        return popClickListener;
    }

    public void setPopClickListener(PopClickListener popClickListener) {
        this.popClickListener = popClickListener;
    }

    public void ShowPop(Activity activity, View rootView, int gravity, String title, String tips) {
        if (pop != null) {
            pop.dismiss();
        }
        View view = activity.getLayoutInflater().inflate(R.layout.pop_common, null);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_tips = (TextView) view.findViewById(R.id.tv_tips);
        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
            tv_title.setVisibility(View.VISIBLE);
        } else {
            tv_title.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(tips)) {
            tv_tips.setText(tips);
            tv_tips.setVisibility(View.VISIBLE);
        } else {
            tv_tips.setVisibility(View.GONE);
        }
        TextView tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                popClickListener.onCancel();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popClickListener.onConfirm();
                pop.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popClickListener.onCancel();
                pop.dismiss();
            }
        });
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.showAtLocation(rootView, gravity, 0, 0);
    }


    public interface PopClickListener {
        public void onConfirm();

        public void onCancel();
    }

}
