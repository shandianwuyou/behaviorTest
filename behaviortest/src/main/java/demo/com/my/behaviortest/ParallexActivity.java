package demo.com.my.behaviortest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhaopeng on 2018/4/18.
 */

public class ParallexActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_parallex);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbarlayout);
        //设置展开时标题颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#ff0000"));
        //设置收缩后标题颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#0000ff"));

    }
}
