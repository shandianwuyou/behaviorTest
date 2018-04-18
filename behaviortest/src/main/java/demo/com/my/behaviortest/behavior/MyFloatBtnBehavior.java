package demo.com.my.behaviortest.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhaopeng on 2018/4/16.
 */

public class MyFloatBtnBehavior extends FloatingActionButton.Behavior {

    public MyFloatBtnBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child, @NonNull View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.i("我的消息", "onNestedScroll: dyConsumed -- " + dyConsumed);
        if(dyConsumed > 0){//这里的规则可以自己定义
            child.animate()
                    .scaleY(1.0f)
                    .scaleX(1.0f)
                    .setDuration(400)
                    .start();
        }else if(dyConsumed < 0){
            child.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .setDuration(400)
                    .start();
        }
    }
}
