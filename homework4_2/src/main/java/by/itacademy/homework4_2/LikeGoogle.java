package by.itacademy.homework4_2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class LikeGoogle extends View {

    private int centerX;
    private int centerY;

    private int bigRadius=500;
    private int smallRadius=100;

    private Paint paint;

    public LikeGoogle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LikeGoogle);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(typedArray.getColor(R.styleable.LikeGoogle_viewColor,0));
        paint.setStyle(Paint.Style.STROKE);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = MeasureSpec.getSize(widthMeasureSpec) / 2;
        centerY = MeasureSpec.getSize(heightMeasureSpec) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(centerX,centerY,bigRadius,paint);
        canvas.drawLine(centerX-bigRadius,centerY,centerX+bigRadius,centerY,paint);
        canvas.drawLine(centerX,centerY-bigRadius,centerX,centerY+bigRadius,paint);
        canvas.drawCircle(centerX,centerY,smallRadius,paint);
        super.onDraw(canvas);
    }
}
