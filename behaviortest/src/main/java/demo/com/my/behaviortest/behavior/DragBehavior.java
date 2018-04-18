package demo.com.my.behaviortest.behavior;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

import demo.com.my.behaviortest.weight.DragView;

/**
 * Created by zhaopeng on 2018/4/17.
 */

public class DragBehavior extends CoordinatorLayout.Behavior<DragView> {

    private int minHeight;
    private int maxHeight;

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, DragView child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        int childHeight = child.getHeaderHeight();
        int childIndex = parent.indexOfChild(child);
        child.offsetTopAndBottom(childHeight * childIndex);
        minHeight = child.getTop();
        maxHeight = parent.getHeight() - childHeight * (parent.getChildCount() - childIndex);
        return true;
    }

    //child == directTargetChild  很关键，这样就只是处理正在操作的view了，否则所有的子view都会跟着滑动
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull DragView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL && child == directTargetChild;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull DragView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        consumed[1] = scroll(child, dy);
        scrollOthers(consumed[1], coordinatorLayout, child);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull DragView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        int shift = scroll(child, dyUnconsumed);
        scrollOthers(shift, coordinatorLayout, child);
    }

    private int scroll(View child, int dyUnconsumed){
        int tempHeight = child.getTop() - dyUnconsumed;
        if(tempHeight < minHeight){
            tempHeight = minHeight;
        }else if(tempHeight > maxHeight){
            tempHeight = maxHeight;
        }
        int offset = tempHeight - child.getTop();
        child.offsetTopAndBottom(offset);
        return -offset;
    }

    private void scrollOthers(int shift, CoordinatorLayout parent, DragView child){
        if(shift == 0){
            return;
        }

        if(shift > 0){//向上滑,推动上面的控件
            DragView curView = child;
            DragView preView = getPreviousView(parent, curView);
            while (preView != null){
                int offset = preView.getTop() + preView.getHeaderHeight() - curView.getTop();
                if(offset > 0){
                    preView.offsetTopAndBottom(-offset);
                }
                curView = preView;
                preView = getPreviousView(parent, curView);
            }
        }else{//向下滑
            DragView curView = child;
            DragView nextView = getNextView(parent, curView);
            while (nextView != null){
                int offset = curView.getTop() + curView.getHeaderHeight() - nextView.getTop();
                if(offset > 0){
                    nextView.offsetTopAndBottom(offset);
                }

                curView = nextView;
                nextView = getNextView(parent, curView);
            }
        }
    }

    private DragView getNextView(CoordinatorLayout parent, DragView child) {
        int curIndex = parent.indexOfChild(child);
        int nextIndex = curIndex + 1;
        if(nextIndex < parent.getChildCount()){
            return (DragView) parent.getChildAt(nextIndex);
        }
        return null;
    }

    private DragView getPreviousView(CoordinatorLayout parent, DragView child) {
        int curIndex = parent.indexOfChild(child);
        for(int i = curIndex - 1; i >= 0 ; i--){
            View view = parent.getChildAt(i);
            if(view instanceof DragView){
                return (DragView) view;
            }
        }
        return null;
    }
}
