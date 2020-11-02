package by.itacademy.homework4_2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LikeGoogle extends View {

    private int centerX;
    private int centerY;

    private float actionX;
    private float actionY;

    private final int bigRadius = 500;
    private final int smallRadius = 150;

    private final List<Integer> colors = new ArrayList<>();
    private final List<Paint> sectors = new ArrayList<>();

    public LikeGoogle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LikeGoogle);
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
        colors.addAll(addColors());
        sectors.addAll(createSectors());
        canvas.drawArc(centerX - bigRadius, centerY - bigRadius, centerX + bigRadius, centerY + bigRadius, 0, 90, true, sectors.get(0));
        canvas.drawArc(centerX - bigRadius, centerY - bigRadius, centerX + bigRadius, centerY + bigRadius, 0, -90, true, sectors.get(1));
        canvas.drawArc(centerX - bigRadius, centerY - bigRadius, centerX + bigRadius, centerY + bigRadius, 90, 90, true, sectors.get(2));
        canvas.drawArc(centerX - bigRadius, centerY - bigRadius, centerX + bigRadius, centerY + bigRadius, -90, -90, true, sectors.get(3));
        canvas.drawCircle(centerX, centerY, smallRadius, sectors.get(4));
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            actionX = event.getX();
            actionY = event.getY();
            int result = onSectorTouch(actionX, actionY);
            Toast.makeText(getContext(), String.format("PUSH: x=%s", result), Toast.LENGTH_SHORT).show();
            Random r = new Random();
            Paint paint = new Paint();
            paint.setColor(colors.get(r.nextInt(8)));
            switch (result) {
                case 0: {
                    sectors.set(0, paint);
                    break;
                }
                case 1: {
                    sectors.set(1, paint);
                    break;
                }
                case 2: {
                    sectors.set(2, paint);
                    break;
                }
                case 3: {
                    sectors.set(3, paint);
                    break;
                }
                case 4: {
                    sectors.set(4, paint);
                    break;
                }
            }
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    private List<Integer> addColors() {
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.BLACK);
        colors.add(Color.WHITE);
        colors.add(Color.MAGENTA);
        colors.add(Color.GRAY);
        return colors;
    }

    private List<Paint> createSectors() {
        int i = 0;
        for (Integer color : colors) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
            sectors.add(paint);
            i++;
            if (i == 4) {
                continue;
            }
        }
        return sectors;
    }

    private int onSectorTouch(float x, float y) {
        if (isInSmallCircle(x, y)) {
            return 4;
        }
        return isInSector(x, y);
    }

    private boolean isInSmallCircle(float x, float y) {
        x = x - centerX;
        y = y - centerY;
        int h = (int) Math.sqrt(x * x + y * y);
        if (h < smallRadius) {
            return true;
        }
        return false;
    }

    private boolean isInBigCircle(float x, float y) {
        x = x - centerX;
        y = y - centerY;
        int h = (int) Math.sqrt(x * x + y * y);
        if (h < bigRadius) {
            return true;
        }
        return false;
    }

    private int isInSector(float x, float y) {
        if (x > centerX && y > centerY && !isInSmallCircle(x, y) && isInBigCircle(x, y)) {
            return 0;
        }
        if (x > centerX && y < centerY && !isInSmallCircle(x, y) && isInBigCircle(x, y)) {
            return 1;
        }
        if (x < centerX && y > centerY && !isInSmallCircle(x, y) && isInBigCircle(x, y)) {
            return 2;
        }
        if (x < centerX && y < centerY && !isInSmallCircle(x, y) && isInBigCircle(x, y)) {
            return 3;
        }
        return -1;
    }
}
