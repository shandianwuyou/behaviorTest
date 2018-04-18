package demo.com.my.behaviortest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatBtnActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.float_btn)
    FloatingActionButton float_btn;

    private MyAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_float_btn);
        ButterKnife.bind(this);

        mAdapter = new MyAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        float_btn.setScaleX(0f);
        float_btn.setScaleY(0f);
    }

    @OnClick(R.id.float_btn)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.float_btn:
                mLinearLayoutManager.scrollToPosition(0);
                float_btn.animate()
                        .scaleY(0f)
                        .scaleX(0f)
                        .setDuration(400)
                        .start();
                break;
        }
    }
}
