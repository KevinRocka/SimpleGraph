package droid.simplegraph;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.List;


/**
 * Author：Rocka on
 * E-mail：Rocka_mail@163.com
 * Time: 2016/3/18 14:43
 * Description: a view to show Graph
 */
public class GraphView extends View {


    private Paint txtPaint;

    private Paint linePaint;

    private Paint rectPaint;

    private Bitmap mLineBitmap;

    private int[] txts_grade;

    private final int COUNT = 5;

    private float txtSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics());

    /**
     * 数字距离左边距的距离
     */
    private int NUM_TO_LEFT_DISTANCE;

    /**
     * 横线距离左边距的距离
     */
    private int LINE_TO_LEFT_DISTANCE;

    /**
     * 横线距离距离右边距的距离
     */
    private int LINE_TO_RIGHT_DISTANCE;

    /**
     * 偏移量
     */
    private int OFFSET;

    private int PADDING_TOP, PADDING_BOTTOM;

    private List<DimensionTO> mGrades;

    public GraphView(Context context) {
        super(context);
        init(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        NUM_TO_LEFT_DISTANCE = ScreenUtil.dip2px(context, 10);
        OFFSET = ScreenUtil.dip2px(getContext(), 6);

        LINE_TO_LEFT_DISTANCE = ScreenUtil.dip2px(context, 30);
        LINE_TO_RIGHT_DISTANCE = LINE_TO_LEFT_DISTANCE;

        PADDING_TOP = ScreenUtil.dip2px(context, 20);
        PADDING_BOTTOM = PADDING_TOP;

        txts_grade = context.getResources().getIntArray(R.array.review_grade_score);
        mLineBitmap = android.graphics.BitmapFactory.decodeResource(context.getResources(), R.drawable.line_listview);

        initPaint(context);
    }

    public void refreshView(){
        this.invalidate();
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {

        canvas.drawColor(Color.WHITE);

        int num_x = NUM_TO_LEFT_DISTANCE;
        int line_y;
        if (mGrades == null) {
            return;
        }

        int scale = (getWidth() - LINE_TO_LEFT_DISTANCE - LINE_TO_RIGHT_DISTANCE) / 5;
        line_y = PADDING_TOP - OFFSET;
        canvas.drawBitmap(mLineBitmap, getBitmapRect(line_y), getBitmapRect(line_y), linePaint);
        for (int i = 0; i < COUNT; i++) {
            DimensionTO item = mGrades.get(i);
            if (item == null || item.getDimsName() == null) {
                continue;
            }
            // 画数字
            if (0 < i) {
                num_x = NUM_TO_LEFT_DISTANCE + OFFSET;
            }
            canvas.drawText(String.valueOf(txts_grade[i]), num_x, PADDING_TOP
                    + ((getHeight() - PADDING_BOTTOM - PADDING_TOP) / COUNT) * i, txtPaint);

            // 画线
            line_y = PADDING_TOP + ((getHeight() - PADDING_BOTTOM - PADDING_TOP) / COUNT) * (i + 1) - OFFSET;
            canvas.drawBitmap(mLineBitmap, getBitmapRect(line_y), getBitmapRect(line_y), linePaint);

            int txtwidth = (int) txtPaint.measureText(item.getDimsName());
            // 画分类文字
            canvas.drawText(mGrades.get(i).getDimsName(), LINE_TO_LEFT_DISTANCE + scale * i + scale / 2 - txtwidth / 2,
                    getHeight() - OFFSET, txtPaint);
        }

        for (int i = 0; i < COUNT; i++) {
            // 画柱状图
            canvas.drawRect(getRect(i, scale), rectPaint);
        }

    }

    private void initPaint(Context context) {
        txtPaint = new Paint();
        txtPaint.setAntiAlias(true);
        txtPaint.setColor(context.getResources().getColor(R.color.text_light_black));
        txtPaint.setTextSize(txtSize);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        // linePaint.setStrokeWidth( 0.15f);
        linePaint.setColor(context.getResources().getColor(R.color.black));

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(getResources().getColor(R.color.orange));
    }

    /**
     * 获取柱状图矩形
     */
    private Rect getRect(int index, int scale) {
        DimensionTO grade = mGrades.get(index);
        if (grade != null) {
            int x = LINE_TO_LEFT_DISTANCE + scale * index + scale / 2;
            Rect rect = new Rect();
            rect.left = x - OFFSET;
            rect.right = x + OFFSET;
            rect.bottom = PADDING_TOP + ((getHeight() - PADDING_BOTTOM - PADDING_TOP) / COUNT) * 5 - OFFSET;
            rect.top =
                    PADDING_TOP + (getHeight() - PADDING_BOTTOM - PADDING_TOP) / COUNT / 2 * (10 - (int) grade.getScore()) - OFFSET;
            return rect;
        } else {
            return null;
        }
    }

    /**
     * 获取柱状图矩形 ...
     */
    private Rect getBitmapRect(int y) {
        Rect rect = new Rect();
        rect.left = LINE_TO_LEFT_DISTANCE;
        rect.right = getWidth() - LINE_TO_RIGHT_DISTANCE;
        rect.bottom = y + 1;
        rect.top = y;
        return rect;
    }

    public void setGradeItem(List<DimensionTO> grades) {
        if (grades != null && !grades.isEmpty()) {
            this.mGrades = grades;
            invalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(widthMeasureSpec, height);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

