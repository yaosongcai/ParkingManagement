package com.tcc.parkingmanagement.data.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.tcc.parkingmanagement.R;


/**
 * <p>
 * 圆环视图
 * 定义了一个简单的动画{@link Animation}
 * 定义了xml的属性
 *
 * @attr ref R.styleable#CarFlowView_CarAnimatColor     动画颜色
 * @attr ref R.styleable#CarFlowView_CarArcColor        圆环颜色
 * @attr ref R.styleable#CarFlowView_CarDrawable        中心图片
 * @attr ref R.styleable#CarFlowView_CarFlowSize        圆环尺寸
 * </p>
 * <p>
 * Created by Administrator on 2017-07-03 0003.
 */

public class CarFlowView extends View {

    private int mArcColor;//圆环颜色
    private int mArcColorDefault = Color.rgb(255, 255, 255);//圆环默认颜色#efefef
    private int mAnimationColor;//动画颜色
    private int mAnimationClorDefault = Color.rgb(2, 114, 240);//动画默认颜色#0272f0
//    private int mRectColor;//矩形边框颜色
//    private int mRectColorDefault = Color.rgb(195, 195, 195);//矩形边框默认颜色#c3c3c3
//    private int mPlateTextColor;//车牌文字颜色
//    private int mPlateTextColorDefault;//车牌文字默认颜色

    private float mArcSize;//圆环尺寸
    private int mArcSizeDefault = 10;//圆环默认尺寸 10dp
    private float mHalfSize;//上半部分圆柱宽度

    private Paint mArcPaint;//圆环画笔

    private Paint mAnimatPaint;//动画画笔

    private int mWidth;//宽度
    private int mHeight;//高度

    private int mAllWidth;//组件宽度
    private int mAllHeight;//组件高度

    private int mWmWidth;//屏幕宽度
    private int mWmHeight;//屏幕高度

    private Paint mHalfPaint;//上半部分半圆画笔

    private float startAngles = -87;//起始弧形开始角度

    private float sweepAngles;//结束弧形角度
    private boolean isEnd;//动画是否结束
    private float mSweepAngles;//变化的动画圆弧值

    private MyAnimation animation;//动画  动画时间暂时定位2秒钟

    private Paint mFlowPaint;//边框画笔

    private Paint mAddPaint;//加号画笔

    private boolean isAdd;//是否绘制加号

    private Drawable mDefauDraw;//添加车辆图片

    private Paint mLinePaint;//画线的画笔

    private Path mLinePath;


    public CarFlowView(Context context) {
        this(context, null);
    }

    public CarFlowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarFlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CarFlowView, defStyleAttr, 0);
//
//        for (int i = 0; i < array.getIndexCount(); i++) {
//            int attr = array.getIndex(i);
//            switch (attr) {
//                case R.styleable.CarFlowView_CarAnimatColor:
//                    mAnimationColor = array.getColor(attr, mAnimationClorDefault);
//                    break;
//                case R.styleable.CarFlowView_CarArcColor:
//                    mArcColor = array.getColor(attr, mArcColorDefault);
//                    break;
//                case R.styleable.CarFlowView_CarDrawable:
//                    mDrawable = array.getDrawable(attr);
//                    break;
//                case R.styleable.CarFlowView_CarFlowSize:
//                    mArcSize = array.getDimension(attr, dip2px(getContext(), mArcSizeDefault));
//                    break;
//                case R.styleable.CarFlowView_CarAddDrawable:
//                    mDefauDraw = array.getDrawable(attr);
//                    break;
//            }
//        }
//
//        array.recycle();

        init();

    }

    private void init() {

        if (mArcSize == 0)
            mArcSize = dip2px(getContext(), mArcSizeDefault);

        mHalfSize = (float) (mArcSize * 0.6);

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcSize);
        if (mArcColor == 0)
            mArcColor = mArcColorDefault;
        mArcPaint.setColor(mArcColor);

        mAnimatPaint = new Paint();
        mAnimatPaint.setAntiAlias(true);
        mAnimatPaint.setStrokeCap(Paint.Cap.ROUND);//圆角   画笔样式
        mAnimatPaint.setStyle(Paint.Style.STROKE);
        mAnimatPaint.setStrokeWidth(mArcSize);
        if (mAnimationColor == 0)
            mAnimationColor = mAnimationClorDefault;
        mAnimatPaint.setColor(mAnimationColor);

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        mWmWidth = wm.getDefaultDisplay().getWidth();
        mWmHeight = wm.getDefaultDisplay().getHeight();

        mHalfPaint = new Paint();
        mHalfPaint.setAntiAlias(true);
        mHalfPaint.setStrokeWidth(mHalfSize);
        mHalfPaint.setColor(mAnimationColor);
        mHalfPaint.setStyle(Paint.Style.FILL);

        mFlowPaint = new Paint();
        mFlowPaint.setAntiAlias(true);
        mFlowPaint.setStyle(Paint.Style.STROKE);
        mFlowPaint.setStrokeWidth(dip2px(getContext(), 2));
        mFlowPaint.setColor(Color.rgb(121, 121, 121));

        mAddPaint = new Paint();
        mAddPaint.setAntiAlias(true);
        mAddPaint.setStyle(Paint.Style.FILL);
        mAddPaint.setStrokeWidth(dip2px(getContext(), 10));
        mAddPaint.setColor(Color.rgb(153, 153, 153));//#999999

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.rgb(108, 139, 216));//#6c8bd8

        mLinePath = new Path();

        animation = new MyAnimation();
        animation.setDuration(2000);
    }

    /**
     * <p>
     * 本测量是基于中心车辆图片的宽度来确定的逐渐宽度为图片宽度 + 20dp  或者为屏幕宽度
     * 存在最小宽度，并没有做过多的屏幕适配
     * </p>
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        switch (widthMode) {
            case MeasureSpec.EXACTLY://match_parent
                mWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST://wrap_content
                mWidth = dip2px(getContext(), 40);

                break;
            case MeasureSpec.UNSPECIFIED://0或者为设置宽高
                int width = (widthSize >= mWmWidth) ? mWmWidth : widthSize;
                mWidth = (width <= 0) ? dip2px(getContext(), 40) : width;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mHeight = mWidth;
                break;

        }


        setMeasuredDimension(mWidth, mHeight);

    }

    private int mRadius;

    @Override
    protected void onDraw(Canvas canvas) {

        drawCir(canvas);//画圆环

        if (sweepAngles != 0) {
            drawAnimat(canvas);//画动画

            drawLines(canvas);//画线
        }

    }

    /**
     * 画线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        float x1 = mWidth / 2 + ((mRadius * 9) / 11);//x轴移动3/5
        float y1 = (mHeight / 2 + dip2px(getContext(), 10)) - ((mRadius * 6) / 7);//y轴移动3/5
        mLinePath.moveTo(x1, y1);
        mLinePath.lineTo(x1, y1);
        float x2 = mWidth / 2 + mRadius;//x轴移动剩下2/5
        float y2 = (mHeight / 2 + dip2px(getContext(), 10)) - mRadius;//y轴移动剩下2/5
        mLinePath.lineTo(x2, y2);

        float x3 = mWidth - dip2px(getContext(), 10);//据右边10dp
        mLinePath.lineTo(x3, y2);

        canvas.drawPath(mLinePath, mLinePaint);
    }

    int radius;
    int maxbottom;
    int rbottom;

    /**
     * <p>
     * 画动画
     * 暂时定死上半部分超出的轨迹部分为5dp
     * </p>
     *
     * @param canvas
     */
    private void drawAnimat(Canvas canvas) {

        radius = (int) (mHalfSize / 2);
        int left = (mWidth / 2) - radius;
        int top = (int) ((mHeight / 2) - mRadius + dip2px(getContext(), 10) - mArcSize - dip2px(getContext(), 5) - radius);
        int right = (mWidth / 2) + radius;
        int bottom = (int) ((mHeight / 2) - mRadius + dip2px(getContext(), 10) - mArcSize - dip2px(getContext(), 5) + radius);

        canvas.drawArc(new RectF(left, top, right, bottom), 0, -180, false, mHalfPaint);//画上方圆柱形的半圆顶部

        int rleft = left;
        int rtop = top + radius;
        int rright = right;
        maxbottom = (int) ((mHeight / 2) - mRadius + dip2px(getContext(), 10) + (mArcSize / 2));
        canvas.drawRect(new RectF(rleft, rtop, rright, rbottom), mHalfPaint);//画上方圆柱形的矩形部分

        int aleft = (mWidth / 2) - mRadius;
        int atop = (mHeight / 2) - mRadius + dip2px(getContext(), 10);
        int aright = (mWidth / 2) + mRadius;
        int abottom = (mHeight / 2) + mRadius + dip2px(getContext(), 10);

        canvas.drawArc(new RectF(aleft, atop, aright, abottom), startAngles, mSweepAngles, false, mAnimatPaint);


    }

    /**
     * 画圆环
     *
     * @param canvas
     */
    private void drawCir(Canvas canvas) {
        mRadius = (Math.min(mWidth, mHeight) - dip2px(getContext(), 30)) / 2;//半径
        int left = (mWidth / 2) - mRadius;
        int top = (mHeight / 2) - mRadius + dip2px(getContext(), 10);
        int right = (mWidth / 2) + mRadius;
        int bottom = (mHeight / 2) + mRadius + dip2px(getContext(), 10);
        canvas.drawArc(new RectF(left, top, right, bottom), -90, 360, false, mArcPaint);
    }

    /**
     * 设置圆环颜色
     *
     * @param mArcColor
     */
    public void setmArcColor(int mArcColor) {
        this.mArcColor = mArcColor;
        invalidate();
    }

    /**
     * 设置圆环动画颜色
     *
     * @param mAnimationColor
     */
    public void setmAnimationColor(int mAnimationColor) {
        this.mAnimationColor = mAnimationColor;
        invalidate();
    }

    /**
     * 设置圆环尺寸
     *
     * @param mArcSize
     */
    public void setmArcSize(float mArcSize) {
        this.mArcSize = mArcSize;
        invalidate();
    }

    /**
     * 设置上方圆柱宽度
     * 默认为0.5倍的圆环宽度
     *
     * @param mHalfSize
     */
    public void setmHalfSize(float mHalfSize) {
        this.mHalfSize = mHalfSize;
        invalidate();
    }

    /**
     * 设置圆环动画的轨迹圆弧弧度
     *
     * @param sweepAngles
     */
    public void setSweepAngles(float sweepAngles) {
        this.sweepAngles = sweepAngles;
        invalidate();
    }

    /**
     * 设置圆环动画的起始角度
     *
     * @param startAngles
     */
    public void setStartAngles(float startAngles) {
        this.startAngles = startAngles;
        invalidate();
    }

    /**
     * 设置是否绘制加号
     *
     * @param add
     */
    public void setAdd(boolean add) {
        isAdd = add;
        invalidate();
    }

    /**
     * 启动动画
     */
    public void startCustomAnimation() {
        this.startAnimation(animation);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    class MyAnimation extends Animation {

        public MyAnimation() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {
                if (rbottom >= maxbottom) {
                    mSweepAngles = sweepAngles * interpolatedTime;
                } else {
                    rbottom = (rbottom + 10 > maxbottom) ? maxbottom : (rbottom + 10);
                }
            } else {
                rbottom = maxbottom;
                mSweepAngles = sweepAngles;
            }
            postInvalidate();

        }
    }
}
