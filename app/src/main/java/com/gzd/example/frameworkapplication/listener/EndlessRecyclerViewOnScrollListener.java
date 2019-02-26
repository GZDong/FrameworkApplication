package com.gzd.example.frameworkapplication.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gzd.example.frameworkapplication.adapter.PersonAdapter;

/**
 * Created by gzd on 2019/2/26 0026
 */
public abstract class EndlessRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {

    private int currentPage = 1;
    private boolean loading = false;   //防止串行连续发送

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {   //其实就是检测到滑到最后一项了，然后调用回调通知，同时开启进度条
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
//        Log.e("TAG", "onScrolled: " + recyclerView.getAdapter().getItemCount() + " --- " + lm.findLastCompletelyVisibleItemPosition() );
        if (recyclerView.getAdapter().getItemCount() == lm.findLastCompletelyVisibleItemPosition() + 1 && loading == false){
            loading = true;
            PersonAdapter adapter = (PersonAdapter) recyclerView.getAdapter();
            adapter.openProgress();
            currentPage +=1;
            onLoadMore(currentPage);
        }
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public abstract void onLoadMore(int currentPage);
}
