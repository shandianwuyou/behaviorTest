package demo.com.my.behaviortest;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.float_btn)
    Button float_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.float_btn, R.id.bottom_dialog, R.id.user_test})
    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.float_btn:
                intent = new Intent(MainActivity.this, FloatBtnActivity.class);
                startActivity(intent);
                break;
            case R.id.bottom_dialog:
                intent = new Intent(MainActivity.this, BottomSheetActivity.class);
                startActivity(intent);
                break;
            case R.id.user_test:
                intent = new Intent(MainActivity.this, UserTestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
