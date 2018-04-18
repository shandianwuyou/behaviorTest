package demo.com.my.behaviortest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhaopeng on 2018/4/16.
 */

public class BottomSheetActivity extends AppCompatActivity{

    @BindView(R.id.bottom_container)
    NestedScrollView bottom_container;

    private BottomSheetDialog dialog;
    private BottomSheetBehavior behavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_bs_dialog);
        ButterKnife.bind(this);

        behavior = BottomSheetBehavior.from(bottom_container);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.i("我的消息", "onStateChanged: newState -- " + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @OnClick({R.id.button, R.id.bottom_sheet})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                dialog = new BottomSheetDialog(BottomSheetActivity.this);
                View cotentView = LayoutInflater.from(BottomSheetActivity.this).inflate(R.layout.dialog_bottom_sheet, null);
                dialog.setContentView(cotentView);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                break;
            case R.id.bottom_sheet:
                if(behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else{
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
            return;
        }

        if(behavior != null && behavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }
        super.onBackPressed();
    }
}
