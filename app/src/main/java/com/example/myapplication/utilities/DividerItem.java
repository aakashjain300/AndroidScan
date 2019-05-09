package com.example.myapplication.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItem extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable divider;
    Paint paint;

    /**
     * Default divider will be used
     */
    public DividerItem(Context context) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        divider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();


        this.paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(22f);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.LEFT);
    }

    /**
     * Custom divider will be used
     */
    public DividerItem(Context context, int resId) {
        divider = ContextCompat.getDrawable(context, resId);
    }

/*
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        c.drawText("and",0, 0, paint);
        divider.draw(c);
    }
*/
}