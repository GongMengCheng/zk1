package gongmengcheng.bwie.com.gongmengcheng201879.MyView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import gongmengcheng.bwie.com.gongmengcheng201879.R;

@SuppressLint("AppCompatCustomView")
public class View extends TextView {
    //定义画笔
    Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;
    private Bitmap moutbitmap;
    Path path;
    String mText;
    Rect TextBound;
    int bgcolor;
    int cx;
    int cy;
    public View(Context context) {
        super(context);

    }
    public View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //初始化
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        //设置画笔属性
        setupOutPaint();
        canvas.drawColor(Color.parseColor("#c0c0c0"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawBitmap(moutbitmap,0,0,null);
        canvas.drawBitmap(bitmap,0,0,null);
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                cx  = x;
                cy =y;
                path.moveTo(cx,cy);
                break;
            case MotionEvent.ACTION_UP:
                int dx = Math.abs(x - cx);
                int dy = Math.abs(y - cy);
                if(dx>3 || dy >3){
                    path.lineTo(x,y);
                }
                cx = x;
                cy = y;
                break;
        }
        invalidate();
        return true;
    }

    private void setupOutPaint() {
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(60);
    }

    private void init() {
         paint = new Paint();
         path = new Path();
        BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
        mText = "刮刮看咯~";
        TextBound = new Rect();
    }

}
