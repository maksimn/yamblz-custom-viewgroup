package ru.yandex.yamblz.viewgroups;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
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
        // Measurement will ultimately be computing these values.
        int height = 0;
        int width = 0;
        int childState = 0;

        int parentWidthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int parentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        final View child = getChildAt(0);

        if (child.getVisibility() != GONE) {
            measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
            width = child.getMeasuredWidth(); // 96
            height = child.getMeasuredHeight(); // 96
            childState = combineMeasuredStates(childState, child.getMeasuredState()); // 0
        }

        // Report our final dimensions.
        setMeasuredDimension(
            resolveSizeAndState(width, parentWidthMeasureSpec, childState),
            resolveSizeAndState(height, parentHeightMeasureSpec, childState)
        );
    }

    /**
     * Position all children within this layout.
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        final View child = getChildAt(0);

        if (child.getVisibility() != GONE) {
            // Place the child.
            child.layout(left, top, right, bottom);
        }
    }
}
