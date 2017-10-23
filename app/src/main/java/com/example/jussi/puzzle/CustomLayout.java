package com.example.jussi.puzzle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MotionEventCompat;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by jussi on 10/18/17.
 *
 * not used
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class CustomLayout extends ViewGroup {

    private int[] randomImages = {R.drawable.sample_1, R.drawable.sample_2, R.drawable.sample_3, R.drawable.sample_4,
            R.drawable.sample_5, R.drawable.sample_6, R.drawable.sample_7, R.drawable.sample_0};


    /**
     * The amount of space used by children in the left gutter.
     */
    private int mLeftWidth;

    /**
     * The amount of space used by children in the right gutter.
     */
    private int mRightWidth;

    /**
     * These are used for computing child frames based on their gravity.
     */
    private Rect mTmpContainerRect = new Rect();
    private final Rect mTmpChildRect = new Rect();

    private ImageView imageView;
    private ImageView child;
    private int[] mThumbIds1 = new int[16];
    private Map<Integer,Integer> id = new HashMap<Integer,Integer>();
    private List<ImageView >imageSet = new ArrayList<ImageView>();
    private int onlayoutTimes=0;


    public CustomLayout(Context context) {
        super(context);


        Random rand = new Random();
        int j = 0;
        //Fill table with images
        for (int i = 0; i < randomImages.length; i++) {
            mThumbIds1[j] = randomImages[i];
            mThumbIds1[j += 1] = randomImages[i];
            j++;
        }

        int temp;
        int randindex;
        //Confuse images in table
        for (int i = 0; i < mThumbIds1.length; i++) {
            temp = mThumbIds1[i];
            randindex = rand.nextInt(15 - 0 + 1);
            mThumbIds1[i] = mThumbIds1[randindex];
            mThumbIds1[randindex] = temp;

        }


    }


    public CustomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * Any layout manager that doesn't scroll will want this.
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }



    public Rect getRec(){

        return this.mTmpChildRect;

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int count = getChildCount();

        for (int i = 0; i < count; i++) {
             child = (ImageView) getChildAt(i);

            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView view =(ImageView) v;


                           view.setImageResource(R.drawable.sample_5);

                  // Log.d("id1",String.valueOf(id.containsKey(view.getId())));
                    Log.d("ID", String.valueOf(view.getId()));


                }
            });
        }



        return super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {



        return super.onTouchEvent(event);
    }


    @Override
    public boolean callOnClick() {

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        Log.d("WIDTHMEASURE", String.valueOf(widthMeasureSpec));


        // These keep track of the space we are using on the left and right for
        // views positioned there; we need member variables so we can also use
        // these for layout later.
        mLeftWidth = 0;
        mRightWidth = 0;

        // Measurement will ultimately be computing these values.
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;

        // Iterate through all children, measuring them and computing our dimensions
        // from their size.
        for (int i = 0; i < count; i++) {
           child = (ImageView) getChildAt(i);
            if (child.getVisibility() != GONE) {
                // Measure the child.
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

            }
        }

        // Total width is the maximum width of all inner children plus the gutters.
        maxWidth += mLeftWidth + mRightWidth;

        // Check against our minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());


        // Report our final dimensions.
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec,
                        childState << MEASURED_HEIGHT_STATE_SHIFT));
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        Log.d("onlayout", String.valueOf(count));

        // These are the far left and right edges in which we are performing layout.
        int leftPos = getPaddingLeft();


        // This is the middle region inside of the gutter.
        //  final int middleLeft = leftPos + mLeftWidth;
        // final int middleRight = rightPos - mRightWidth;

        // These are the top and bottom edges in which we are performing layout.
        int parentTop = getPaddingTop();
        int parentBottom = bottom - top - getPaddingBottom();

        for (int i = 0; i < count; i++) {
           child = (ImageView) getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                final int width = this.getWidth() / 4;
                // final int width = child.getMeasuredWidth();
                // final int height = child.getMeasuredHeight();
                final int height = this.getHeight() / 5;
                // Compute the frame in which we are placing this child.
                if (i == 5 || i == 9 || i == 13) {
                    leftPos = getPaddingLeft();
                    parentBottom = bottom - top - getPaddingBottom() - height;
                    parentTop = parentTop + getPaddingTop() + height/2;
                }

                mTmpContainerRect.left = leftPos + lp.leftMargin + 2;
                mTmpContainerRect.right = leftPos + width + lp.rightMargin + 2;
                leftPos = mTmpContainerRect.right+=2;


                mTmpContainerRect.top = parentTop + lp.topMargin;
                mTmpContainerRect.bottom = parentBottom - lp.bottomMargin;

                // Use the child's gravity and size to determine its final
                // frame within its container.

                Gravity.apply(lp.gravity, width, height, mTmpContainerRect, mTmpChildRect);
                if(onlayoutTimes==0) {
                    child.setImageResource(R.drawable.sample_10);
                    id.put(child.getId(), i);
                }
                child.layout(mTmpChildRect.left, mTmpChildRect.top,
                        mTmpChildRect.right, mTmpChildRect.bottom);


            }


        }


          onlayoutTimes++;

    }



    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new CustomLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }




    public static class LayoutParams extends MarginLayoutParams {
        /**
         * The gravity to apply with the View to which these layout parameters
         * are associated.
         */
        public int gravity = Gravity.TOP | Gravity.START;
        //   public int gravity = Gravity.FILL;
        public static int POSITION_MIDDLE = 0;
        public static int POSITION_LEFT = 1;
        public static int POSITION_RIGHT = 2;

        public int position = POSITION_MIDDLE;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            // Pull the layout param values from the layout XML during
            // inflation.  This is not needed if you don't care about
            // changing the layout behavior in XML.
           TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.CustomLayoutLP);
            gravity = a.getInt(R.styleable.CustomLayoutLP_android_layout_gravity, gravity);
            position = a.getInt(R.styleable.CustomLayoutLP_layout_position, position);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }



}
