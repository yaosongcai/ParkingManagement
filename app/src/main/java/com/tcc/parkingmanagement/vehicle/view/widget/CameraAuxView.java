package com.tcc.parkingmanagement.vehicle.view.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.util.ScreenUtils;

/**
 * 扫描框辅助线
 * Created by admin on 2018/8/25 16:03
 * 邮箱：yaosongcai@ujifu.com
 */

public class CameraAuxView extends View{

    private Context mContext;

    private int type;//扫描框辅助线位置  0-左上 1-右上  2-左下  3-右下

    private Paint paint;

    public CameraAuxView(Context context) {
        super(context);
    }

    public CameraAuxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        TypedArray ar = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CameraAuxView,0,0);

        for (int i = 0; i < ar.getIndexCount();i++){
            int attr = ar.getIndex(i);
            switch (attr){
                case R.styleable.CameraAuxView_type:
                    type = ar.getInteger(attr,0);
                    break;
            }
        }
        ar.recycle();

        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(ScreenUtils.dp2px(mContext,3));
        paint.setColor(Color.parseColor("#00ff00"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (type){
            case 0:
                canvas.drawLine(0,0,0,getHeight(),paint);
                canvas.drawLine(0,0,getWidth(),0,paint);
                break;
            case 1:
                canvas.drawLine(getWidth(),0,0,0,paint);
                canvas.drawLine(getWidth(),0,getWidth(),getHeight(),paint);
                break;
            case 2:
                canvas.drawLine(0,getHeight(),0,0,paint);
                canvas.drawLine(0,getHeight(),getWidth(),getHeight(),paint);
                break;
            case 3:
                canvas.drawLine(getWidth(),getHeight(),0,getHeight(),paint);
                canvas.drawLine(getWidth(),getHeight(),getWidth(),0,paint);
                break;
        }
    }
}
