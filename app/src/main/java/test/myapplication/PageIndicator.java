package test.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/8/26.
 */
public class PageIndicator extends View {

    private static final int DEFAULT_BACK_COLOR=0xff000000;
    private static final int DEFAULT_FRONT_COLOR=0xffffffff;

    protected Context ctx;

    private int mCount;
    private int mBackColor;
    private int mFrontColor;
    private PageIndicatorAdapter mAdapter;
    private ViewPager mViewPager;
    private int mPosition;
    private int mOffset;
    private Paint mPaint;
    private TextView t;
    private int mTextSize;
    private Scroller mScroller;
    private boolean IsLayout=false;

    private ViewPager.OnPageChangeListener mOnPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if(mPosition!=position){
                int delta=position*getWidth()-getScrollX();
                mScroller.startScroll(getScrollX(),0,delta,0,500);
                mPosition=position;
                invalidate();
            }
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };




    public PageIndicator(Context context) {
        super(context);
        ctx=context;
        init();
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ctx=context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PageIndicator,defStyleAttr,0);
        mCount=a.getInteger(R.styleable.PageIndicator_amount,0);
        mBackColor = a.getColor(R.styleable.PageIndicator_back_color, DEFAULT_BACK_COLOR);
        mFrontColor=a.getColor(R.styleable.PageIndicator_front_color, DEFAULT_FRONT_COLOR);
        mTextSize=(int)a.getDimension(R.styleable.PageIndicator_text_size,10);
        a.recycle();
        //setOnClickListener(this);
        mScroller=new Scroller(ctx);

        init();
    }

    private void init(){
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPosition=0;
        mOffset=0;
    }

    public void setAdapter(PageIndicatorAdapter adapter){
        mAdapter=adapter;
        mCount=mAdapter.getCount();
        invalidate();
    }

    public void setViewPager(ViewPager viewpager){
        mViewPager=viewpager;
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(mAdapter!=null){
            mPaint.setColor(Color.GREEN);
            canvas.drawRect(0, 0, getWidth() * mAdapter.getCount(), getHeight(), mPaint);
            mPaint.setColor(mFrontColor);
            mPaint.setTextSize(mTextSize);
            Rect Bounds=new Rect();
            for(int i=0;i<mAdapter.getCount();i++){
                String s=mAdapter.getItem(i);
                mPaint.getTextBounds(s,0,s.length(),Bounds);
                canvas.drawText(s,i*getWidth()+(getWidth()-Bounds.width())/2,getHeight()/2+Bounds.height()/2,mPaint);
            }
        }



    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int width=0;
        int height;


        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else{
            if(mAdapter!=null){
                Paint paint=new Paint();
                paint.setTextSize(mTextSize);
                Rect Bounds=new Rect();
                int temp=0;
                for(int i=0;i<mCount;i++){
                    String s=mAdapter.getItem(i);
                    paint.getTextBounds(s,0,s.length(),Bounds);
                    width=Math.max(temp,Bounds.width());
                    temp=width;
                }
            }else{ width=0;}
        }

        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else{
            height=Math.max(10,getSuggestedMinimumHeight());
        }

        width+=getPaddingLeft()+getPaddingRight();

        setMeasuredDimension(width,height);

    }

    @Override
    public void computeScroll(){
        if(mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(),0);
            postInvalidate();
        }
    }
}
