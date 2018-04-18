package demo.com.my.behaviortest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhaopeng on 2018/4/13.
 */

public class MyAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_a, parent, false);
        return new MyHolder(textView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyHolder){
            MyHolder mHolder = (MyHolder) holder;
            mHolder.textView.setText("我的是第" + position + "项");
        }
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    static class MyHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyHolder(TextView itemView) {
            super(itemView);
            this.textView = itemView;
        }
    }
}
