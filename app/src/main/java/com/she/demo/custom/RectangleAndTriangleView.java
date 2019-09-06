package com.she.demo.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.she.demo.R;
import com.she.demo.utils.AppUtils;

public class RectangleAndTriangleView extends View {

    private static final int DEFAULT_TRI_WIDTH = 10;
    private static final int DEFAULT_TRI_HEIGHT = 6;
    private static final int DEFAULT_RECT_WIDTH = 100;
    private static final int DEFAULT_RECT_HEIGHT = 50;
    private static final int DEFAULT_TRI_LINE_SIZE = 2;
    private static final int DEFUALT_COLOR = R.color.colorAccent;
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int RIGHT = 2;
    private static final int LEFT = 3;
    private Paint mPaint;
    private Paint mLinePaint;
    private int mFillColor;
    private int mLineColor;
    private int mTriWidth;
    private int mTriHeight;
    private int mRectWidth;
    private int mRectHeight;
    private boolean isHaveLine;
    private int mDirection;
    private Path mPath;
    private Path mLinePath;
    private Context context;
    private int mTriLineSize;


    public RectangleAndTriangleView(final Context context) {
        this(context, null);
        this.context = context;
    }

    public RectangleAndTriangleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        this.context = context;
    }

    public RectangleAndTriangleView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RectangleAndTriangleView, 0, 0);
        mFillColor = typedArray.getColor(R.styleable.RectangleAndTriangleView_fill_color, ContextCompat.getColor(getContext(), DEFUALT_COLOR));
        mLineColor = typedArray.getColor(R.styleable.RectangleAndTriangleView_line_color, ContextCompat.getColor(getContext(), DEFUALT_COLOR));
        mTriHeight = typedArray.getDimensionPixelSize(R.styleable.RectangleAndTriangleView_tri_height, DEFAULT_TRI_HEIGHT);
        mTriWidth = typedArray.getDimensionPixelSize(R.styleable.RectangleAndTriangleView_tri_width, DEFAULT_TRI_WIDTH);
        mTriLineSize = typedArray.getDimensionPixelSize(R.styleable.RectangleAndTriangleView_tri_line_size, DEFAULT_TRI_LINE_SIZE);
        isHaveLine = typedArray.getBoolean(R.styleable.RectangleAndTriangleView_isHaveLine, false);
        mDirection = typedArray.getInt(R.styleable.RectangleAndTriangleView_tri_direction, mDirection);
        typedArray.recycle();

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(mTriLineSize);

        mPath = new Path();
        mLinePath = new Path();

        mPaint.setColor(mFillColor);
        mLinePaint.setColor(mLineColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRectWidth = MeasureSpec.getSize(widthMeasureSpec);
        mRectHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (mRectWidth == 0 || widthMode != MeasureSpec.EXACTLY) {
            mRectWidth = AppUtils.dip2px(context, DEFAULT_RECT_WIDTH);
        }
        if (mRectHeight == 0 || heightMode != MeasureSpec.EXACTLY) {
            mRectHeight = AppUtils.dip2px(context, DEFAULT_RECT_HEIGHT);
        }
        switch (mDirection) {
            case TOP:
                setMeasuredDimension(mRectWidth, mRectHeight + mTriHeight);
                break;
            case BOTTOM:
                setMeasuredDimension(mRectWidth, mRectHeight + mTriHeight);
                break;
            case LEFT:
                setMeasuredDimension(mRectWidth+mTriHeight, mRectHeight);
                break;
            case RIGHT:
                setMeasuredDimension(mRectWidth+mTriHeight, mRectHeight);
                break;
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mDirection) {
            case TOP:
                mPath.moveTo(0, mTriHeight);
                mPath.lineTo(mRectWidth / 2 - mTriWidth / 2, mTriHeight);
                mPath.lineTo(mRectWidth / 2, 0);
                mPath.lineTo(mRectWidth / 2 + mTriWidth / 2, mTriHeight);
                mPath.lineTo(mRectWidth, mTriHeight);
                mPath.lineTo(mRectWidth, mRectHeight + mTriHeight);
                mPath.lineTo(0, mRectHeight + mTriHeight);

                if (isHaveLine) {
                    mLinePath.moveTo(0, mTriHeight);
                    mLinePath.lineTo(mRectWidth / 2 - mTriWidth / 2, mTriHeight);
                    mLinePath.lineTo(mRectWidth / 2, 0);
                    mLinePath.lineTo(mRectWidth / 2 + mTriWidth / 2, mTriHeight);
                    mLinePath.lineTo(mRectWidth, mTriHeight);
                    mLinePath.lineTo(mRectWidth, mRectHeight + mTriHeight);
                    mLinePath.lineTo(0, mRectHeight + mTriHeight);
                }
                break;
            case BOTTOM:
                mPath.moveTo(0, 0);
                mPath.lineTo(mRectWidth, 0);
                mPath.lineTo(mRectWidth, mRectHeight);
                mPath.lineTo(mRectWidth / 2 + mTriWidth / 2, mRectHeight);
                mPath.lineTo(mRectWidth / 2, mRectHeight + mTriHeight);
                mPath.lineTo(mRectWidth / 2 - mTriWidth / 2, mRectHeight);
                mPath.lineTo(0, mRectHeight);

                if (isHaveLine) {
                    mLinePath.moveTo(0, 0);
                    mLinePath.lineTo(mRectWidth, 0);
                    mLinePath.lineTo(mRectWidth, mRectHeight);
                    mLinePath.lineTo(mRectWidth / 2 + mTriWidth / 2, mRectHeight);
                    mLinePath.lineTo(mRectWidth / 2, mRectHeight + mTriHeight);
                    mLinePath.lineTo(mRectWidth / 2 - mTriWidth / 2, mRectHeight);
                    mLinePath.lineTo(0, mRectHeight);
                }
                break;
            case RIGHT:
                mPath.moveTo(0, 0);
                mPath.lineTo(mRectWidth, 0);
                mPath.lineTo(mRectWidth, mRectHeight / 2 - mTriWidth / 2);
                mPath.lineTo(mRectWidth+mTriHeight, mRectHeight / 2);
                mPath.lineTo(mRectWidth, mRectHeight / 2 + mTriWidth / 2);
                mPath.lineTo(mRectWidth, mRectHeight);
                mPath.lineTo(0, mRectHeight);

                if (isHaveLine) {
                    mLinePath.moveTo(0, 0);
                    mLinePath.lineTo(mRectWidth, 0);
                    mLinePath.lineTo(mRectWidth, mRectHeight / 2 - mTriWidth / 2);
                    mLinePath.lineTo(mRectWidth+mTriHeight, mRectHeight / 2);
                    mLinePath.lineTo(mRectWidth, mRectHeight / 2 + mTriWidth / 2);
                    mLinePath.lineTo(mRectWidth, mRectHeight);
                    mLinePath.lineTo(0, mRectHeight);
                }
                break;
            case LEFT:
                mPath.moveTo(mTriHeight, 0);
                mPath.lineTo(mTriHeight, mRectHeight / 2 - mTriWidth / 2);
                mPath.lineTo(0, mRectHeight / 2);
                mPath.lineTo(mTriHeight, mRectHeight / 2 + mTriWidth / 2);
                mPath.lineTo(mTriHeight, mRectHeight);
                mPath.lineTo(mRectWidth+mTriHeight, mRectHeight);
                mPath.lineTo(mRectWidth+mTriHeight, 0);

                if (isHaveLine) {
                    mLinePath.moveTo(mTriHeight, 0);
                    mLinePath.lineTo(mTriHeight, mRectHeight / 2 - mTriWidth / 2);
                    mLinePath.lineTo(0, mRectHeight / 2);
                    mLinePath.lineTo(mTriHeight, mRectHeight / 2 + mTriWidth / 2);
                    mLinePath.lineTo(mTriHeight, mRectHeight);
                    mLinePath.lineTo(mRectWidth+mTriHeight, mRectHeight);
                    mLinePath.lineTo(mRectWidth+mTriHeight, 0);
                }
                break;
            default:
                break;
        }

        mPath.close();
        canvas.drawPath(mPath, mPaint);
        if (isHaveLine) {
            mLinePath.close();
            canvas.drawPath(mLinePath, mLinePaint);
        }
    }

    /**
     * 设置实体填充颜色
     * @param mFillColor
     */
    public void setFillColor(int mFillColor){
        this.mFillColor=mFillColor;
        mPaint.setColor(mFillColor);
        invalidate();
    }

    /**
     * 设置线框的颜色
     * @param mLineColor
     */
    public void setLineColor(int mLineColor){
        this.mLineColor=mLineColor;
        mLinePaint.setColor(mLineColor);
        invalidate();
    }

    public void setIsHaveLine(boolean isHaveLine){
        this.isHaveLine=isHaveLine;
        invalidate();
    }

    public boolean isHaveLine() {
        return isHaveLine;
    }
}
