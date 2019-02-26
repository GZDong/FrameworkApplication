package com.gzd.example.frameworkapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gzd.example.frameworkapplication.R;
import com.gzd.example.frameworkapplication.bean.Person;

import java.util.List;

/**
 * Created by gzd on 2019/2/26 0026
 */
public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Person> mList;
    public PersonAdapter(List<Person> persons) {
        mList = persons;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        RecyclerView.ViewHolder viewHolder;
        if (type == 1){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_rv,viewGroup,false);
            viewHolder = new MyViewHolder(view);
        }else {
            Log.e("tag", "onBindViewHolder: new Viewholder");
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_footer,viewGroup,false);
            viewHolder = new ProgressViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
//        Log.e("TAG", "getItemViewType: " + mList.size() + position + " __ " + mList.get(position).getName() + " : " + mList.get(position).getAge() );
        if (mList.get(position) == null){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.mTextView.setText(mList.get(i).getName());
            myViewHolder.mButton.setText(":" + mList.get(i).getAge());
        }else {
            Log.e("tag", "onBindViewHolder: progress" );
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void openProgress(){
        mList.add(null);
        notifyItemInserted(mList.size()-1);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView ;
        Button mButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
            mButton = itemView.findViewById(R.id.button);
        }
    }

    class ProgressViewHolder extends RecyclerView.ViewHolder{
        ProgressBar mProgressBar;

        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
