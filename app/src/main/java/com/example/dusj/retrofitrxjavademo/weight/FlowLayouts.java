package com.example.dusj.retrofitrxjavademo.weight;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/6/14.
 */

public class FlowLayouts extends ViewGroup {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayouts(Context context) {
        this(context,null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayouts(Context context, AttributeSet attrs) {
       super(context,attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取父容器的宽和高 以及测量模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int higth = 0;

        int lineWidth = 0;
        int lineHigth = 0;

        int mCount = getChildCount();
        for (int i = 0; i < mCount; i++) {
            View child = getChildAt(i);

            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lm = (MarginLayoutParams) child.getLayoutParams();
            int widthChild = child.getMeasuredWidth() + lm.leftMargin + lm.rightMargin;
            int higthChild = child.getMeasuredHeight() + lm.bottomMargin + lm.topMargin;

            if (lineWidth + widthChild > widthChild){

            }else {
                lineWidth += widthChild;
                lineWidth = Math.max(lineHigth,higthChild);
            }
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
