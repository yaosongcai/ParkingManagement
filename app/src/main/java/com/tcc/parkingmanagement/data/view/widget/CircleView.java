package com.tcc.parkingmanagement.data.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.util.ScreenUtils;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.view.widget
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/22
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/22
 * 修改备注：
 */
public class CircleView extends View {
    // 画圆环的画笔
    private Paint mRingDefaultPaint;
    // 已用环的画笔
    private Paint mUsePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画白线的画笔
    private Paint mLinePaint;
    // 画字体的画笔
    private Paint mTextPaint;
    //画单位的画笔
    private Paint mText1Paint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    // 字的长度
    private float mTxtWidth;
    // 字的高度
    private float mTxtHeight;
    // 总进度
    private int mTotalProgress = 100;
    // 当前进度
    private int mProgress = 30;
    // 实际展示总进度
    private int mShowProgress;
    // 总收益
    private String totalMoney = "0.00";

    private Context mContext;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CircleView, 0, 0);
        for (int i = 0; i < typeArray.getIndexCount(); i++) {
            int attr = typeArray.getIndex(i);
            switch (attr){
                case R.styleable.CircleView_radius:
                    mRadius = typeArray.getDimension(attr, 80);
                    break;
                case R.styleable.CircleView_strokeWidth:
                    mStrokeWidth = typeArray.getDimension(attr, 10);
                    break;
                case R.styleable.CircleView_circleColor:
                    mCircleColor = typeArray.getColor(attr, 0xFFFFFFFF);
                    break;
                case R.styleable.CircleView_ringColor:
                    mRingColor = typeArray.getColor(attr, 0xFFFFFFFF);
                    break;
            }
        }
        typeArray.recycle();
        mRingRadius = mRadius + mStrokeWidth / 2;
    }

    private void initVariable() {

        //“使用”字画笔设置
        mUsePaint = new Paint();
        mUsePaint.setAntiAlias(true);
        mUsePaint.setStyle(Paint.Style.FILL);
        mUsePaint.setColor(Color.WHITE);
        mUsePaint.setTextSize(ScreenUtils.sp2px(mContext, 10));

        //圆环画笔设置
        mRingDefaultPaint = new Paint();
        mRingDefaultPaint.setAntiAlias(true);
        mRingDefaultPaint.setColor(Color.parseColor("#ffffff"));//e60012
        mRingDefaultPaint.setStyle(Paint.Style.STROKE);
        mRingDefaultPaint.setStrokeWidth(mStrokeWidth);

        //已使用多少圆环画笔设置
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(ScreenUtils.sp2px(mContext, 22));

        mText1Paint = new Paint();
        mText1Paint.setAntiAlias(true);
        mText1Paint.setStyle(Paint.Style.FILL);
        mText1Paint.setColor(Color.WHITE);
        mText1Paint.setTextSize(ScreenUtils.sp2px(mContext, 10));

        mLinePaint = new Paint();
        mLinePaint.setColor(Color.WHITE);


        //获取字体高度
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;

        RectF oval = new RectF();
        oval.left = ScreenUtils.dp2px(mContext,2);
        oval.top = ScreenUtils.dp2px(mContext,2);
        oval.right = getWidth()-ScreenUtils.dp2px(mContext,2);
        oval.bottom = getHeight()-ScreenUtils.dp2px(mContext,2);
        //画整圆弧
        canvas.drawArc(oval, -90, 360, false, mRingDefaultPaint);
        //已使用多少圆弧
        canvas.drawArc(oval, -90, ((float) mProgress / mTotalProgress) * 360, false, mRingPaint);
        //文字绘制
        String txt = "今日收益";
        mTextPaint.setTextSize(ScreenUtils.sp2px(mContext,12));
        //文字的长度
        mTxtWidth = mTextPaint.measureText(txt, 0, txt.length());
        canvas.drawText(txt, getWidth()/2 - mTxtWidth / 2, getHeight()/2- mTxtHeight/2 , mTextPaint);//- mTxtHeight-ScreenUtils.dp2px(mContext,3)


        mText1Paint.setTextSize(ScreenUtils.sp2px(mContext,10));
        Rect _pb1 = new Rect();
        mText1Paint.getTextBounds("¥ ", 0, "¥ ".length(), _pb1);

        mUsePaint.setTextSize(ScreenUtils.sp2px(mContext,20));
        //获取字体高度
        Paint.FontMetrics fm = mUsePaint.getFontMetrics();
        int textheight = (int) Math.ceil(fm.descent - fm.ascent);
        Rect _pb = new Rect();
        mUsePaint.getTextBounds(totalMoney, 0, totalMoney.length(), _pb);
        int perX = getWidth()/2 - _pb.width() / 2;
        canvas.drawText("¥ ", perX - _pb1.width(), getHeight()/2 + textheight/2 +ScreenUtils.dp2px(mContext,7) , mText1Paint);
        canvas.drawText(totalMoney, perX+_pb1.width(), getHeight()/2 + textheight/2 +ScreenUtils.dp2px(mContext,7) , mUsePaint);

    }

    public void setTotalMoney(String usedFlow) {
        this.totalMoney = usedFlow;
    }


    class MyAnimation extends Animation {

        public MyAnimation() {
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f) {

            } else {

            }
            postInvalidate();

        }
    }
}
