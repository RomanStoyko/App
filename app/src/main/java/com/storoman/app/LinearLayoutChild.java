package com.storoman.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

abstract public class LinearLayoutChild extends LinearLayout{
    public LinearLayoutChild(Context context) {
        super(context);
    }

    protected LinearLayoutChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract View getFill();
    public abstract double getCalclation();
}
