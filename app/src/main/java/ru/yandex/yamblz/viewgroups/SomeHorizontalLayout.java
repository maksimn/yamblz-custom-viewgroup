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
        final int layoutFullWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int count = getChildCount();

        // Measurement will ultimately be computing these values.
        int height = 0;
        int width = 0;

        final int parentWidthMSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        final int parentHeightMSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        View childWithMatchParent = null;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getLayoutParams().width == LayoutParams.MATCH_PARENT) {
                childWithMatchParent = child;
            } else if (child.getVisibility() != GONE) {
                measureChild(child, parentWidthMSpec, parentHeightMSpec);
                width += child.getMeasuredWidth();
                height = Math.max(height, child.getMeasuredHeight());
            }
        }

        if (childWithMatchParent != null && childWithMatchParent.getVisibility() != GONE) {
            final int measureSpec =
                    MeasureSpec.makeMeasureSpec(layoutFullWidth - width, MeasureSpec.EXACTLY);

            measureChild(childWithMatchParent, measureSpec, parentHeightMSpec);
            height = Math.max(height, childWithMatchParent.getMeasuredHeight());
        }

        // Report our final dimensions.
        setMeasuredDimension(
            resolveSizeAndState(layoutFullWidth, parentWidthMSpec, 0),
            resolveSizeAndState(height, parentHeightMSpec, 0)
        );
    }

    /**
     * Position all children within this layout.
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();

        for (int i = 0, currentWidth = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() != GONE) {
                int lTop = 0;
                int lLeft = currentWidth;
                int lRight = currentWidth + child.getMeasuredWidth();
                int lBottom = child.getMeasuredHeight();
                currentWidth = lRight;

                // Place the child.
                child.layout(lLeft, lTop, lRight, lBottom);
            }
        }
    }
}
