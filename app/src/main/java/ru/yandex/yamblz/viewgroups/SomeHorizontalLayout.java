package ru.yandex.yamblz.viewgroups;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RemoteViews;

@RemoteViews.RemoteView
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

    /**
     * Any layout manager that doesn't scroll will want this.
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /**
     * Ask all children to measure themselves and compute the measurement of this
     * layout based on the children.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    }

    /**
     * Position all children within this layout.
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    // ----------------------------------------------------------------------
    // SomeHorizontalLayout do not need custom per-child layout parameters (this layout manager does
    // fixed positioning of its children).
}
