package ru.yandex.yamblz.viewgroups;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

import ru.yandex.yamblz.R;

public class SomeHorizontalLayout extends ViewGroup {
    public SomeHorizontalLayout(Context context) {
        super(context);
    }

    public SomeHorizontalLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SomeHorizontalLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray arr = context.obtainStyledAttributes(attrs,
                    R.styleable.SomeHorizontalLayout);
            arr.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
