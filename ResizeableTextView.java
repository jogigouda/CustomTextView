//============================================
// Created by : Jogendra Gouda
// Created on : 28 JULY 2016
//=============================================

package com.ga.lm.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Custom expandable TextView for showing show more option when number of lines is more than 2
 */
public class ResizeableTextView extends TextView {
    private static final String TAG = "ResizeableTextView";
    //Variable to manage number of lines after which "View more..." will show
    private int nLines = 2;
    private String stringToShow = " view more...", originalText;
    private boolean trim;


    public ResizeableTextView(Context context) {
        super(context);
        Log.d(TAG, "Constructor called 0");
    }

    public ResizeableTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.d(TAG, "Constructor called 1");
    }

    public ResizeableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "Constructor called 2");

        //OnClick to handle expand and collapse of text view
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = !trim;
                requestFocusFromTouch();
                setText(originalText);
                invalidate();
            }
        });
    }

    public int getnLines() {
        return nLines;
    }

    public void setnLines(int lines) {
        this.nLines = lines;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        //First time when UI shown to user store original string of text in a variable
            originalText = text.toString();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Check if number of lines is more then given number of lines
        if (getLineCount() > getnLines() && !trim) {
            int length = getLayout().getLineEnd(getnLines() - 1);
            String str=originalText;
            String s = originalText.subSequence(0, length - stringToShow.length()).toString();
            Log.d(TAG, "subSequence " + s);
            setText(Html.fromHtml(s + "<font color='#448AFF'>" + stringToShow + "</font>"));
            originalText=str;
        }

    }
}
