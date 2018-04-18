package demo.com.my.behaviortest.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import demo.com.my.behaviortest.R;
import demo.com.my.behaviortest.SimpleAdapter;
import demo.com.my.behaviortest.behavior.DragBehavior;

/**
 * Created by zhaopeng on 2018/4/17.
 * 比较坑的是我的布局里需要有NestedScrollingChild2的实现类，否则无法响应behavior
 */
@CoordinatorLayout.DefaultBehavior(DragBehavior.class)
public class DragView extends FrameLayout{

    private TextView textView;

    public DragView(@NonNull Context context) {
        this(context, null);
    }

    public DragView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DragView);
        int color = a.getColor(R.styleable.DragView_android_colorBackground, Color.BLACK);
        CharSequence title = a.getText(R.styleable.DragView_android_text);

        LayoutInflater.from(context).inflate(R.layout.view_drag, this);
        textView = findViewById(R.id.root_view);
        textView.setText(title);
        textView.setBackgroundColor(color);

        a.recycle();

        RecyclerView recyclerView = findViewById(R.id.card_recyclerview);

        recyclerView.setAdapter(new SimpleAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildViewHolder(view).getAdapterPosition();
                outRect.set(30, position == 0?30:0, 30, 30);
            }
        });
    }

    public int getHeaderHeight(){
        return textView.getHeight();
    }
}
