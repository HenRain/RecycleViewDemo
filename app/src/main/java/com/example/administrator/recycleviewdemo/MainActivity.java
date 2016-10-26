package com.example.administrator.recycleviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.recycleviewdemo.adapter.NetEaseAdapter;
import com.example.administrator.recycleviewdemo.biz.Xhttp;
import com.example.administrator.recycleviewdemo.entity.NetEase;

import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import view.RecycleViewDivider;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    String url = "http://c.m.163.com/nc/article/list/T1348647909107/0-20.html";
    @BindView(R.id.recyclerview1)
    RecyclerView mRecyclerview1;
    NetEaseAdapter mNetEaseAdapter;
    @BindView(R.id.swipe1)
    SwipeRefreshLayout mSwipe1;
Handler mHandler;
    private String tid="T1348647909107";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mHandler=new Handler();
      //  mSwipe1.setOnRefreshListener(this);
      //  mRecyclerview1.addOnScrollListener(lis);
        Xhttp.getNewsList(url,tid, listener);

    }
private RecyclerView.OnScrollListener lis =new RecyclerView.OnScrollListener(){

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (!mSwipe1.isRefreshing()) {
            int lastItemPosition = mLayoutManager.findLastVisibleItemPosition();
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition == mNetEaseAdapter.getItemCount() - 1) {

                mNetEaseAdapter.setCurrentState(NetEaseAdapter.FOOTER_PULLING);
                Xhttp.getNewsList(url, tid, new Xhttp.OnSuccessListener() {
                    @Override
                    public void setNewsList(List<NetEase> neteaseNews) {

                        mNetEaseAdapter.addDataList(neteaseNews);
                        mNetEaseAdapter.notifyDataSetChanged();
                        if (neteaseNews.size() == 0) {
                            mNetEaseAdapter.setCurrentState(NetEaseAdapter.FOOTER_PULL_NO_DATA);
                        } else {
                            mNetEaseAdapter.setCurrentState(NetEaseAdapter.FOOTER_PULL_FINISHED);
                        }
                    }
                });
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }
};
    private LinearLayoutManager mLayoutManager;
    private Xhttp.OnSuccessListener listener = new Xhttp.OnSuccessListener() {

        @Override
        public void setNewsList(List<NetEase> neteaseNews) {
            Log.d("xhttp", "setNewsList: "+neteaseNews.size());
            mNetEaseAdapter = new NetEaseAdapter(neteaseNews);
            mRecyclerview1.setAdapter(mNetEaseAdapter);
            mLayoutManager =new LinearLayoutManager(MainActivity.this);
            mRecyclerview1.setLayoutManager(mLayoutManager);

            mRecyclerview1.addItemDecoration(new RecycleViewDivider(MainActivity.this, LinearLayoutManager.HORIZONTAL));
        }
    };

    @Override
    public void onRefresh() {
Runnable runnable=new TimerTask() {
    @Override
    public void run() {
        NetEase netEase=mNetEaseAdapter.getDataList().get(1);
        mNetEaseAdapter.addData(1,netEase);
        mNetEaseAdapter.notifyItemInserted(1);
        mSwipe1.setRefreshing(false);

    }
};
        mHandler.postDelayed(runnable,2000);
    }
}
