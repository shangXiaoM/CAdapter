package com.shangxiaom.commonlist.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shangxiaom.commonlist.R;


/**
 * Created by ming on 2017/7/29.
 */

public class ToastUtil {
    private Toast mToast;

    private ToastUtil(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) v.findViewById(R.id.toast_text);
        textView.setText(text);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
    }

    public static ToastUtil makeText(Context context, CharSequence text, int duration) {
        return new ToastUtil(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}
