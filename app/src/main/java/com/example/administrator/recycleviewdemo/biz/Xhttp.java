package com.example.administrator.recycleviewdemo.biz;

import android.util.Log;

import com.example.administrator.recycleviewdemo.entity.NetEase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 */

public class Xhttp {
    private  static final String TAG="xhttp";
    public  static  void getNewsList(String url, final String tid, final OnSuccessListener listener ){
        RequestParams entity=new RequestParams(url);
        Callback.CommonCallback<String > callback= new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "onSuccess: ");
                ArrayList<NetEase> neteaseNews = new ArrayList<>();
                Gson gson =new Gson();
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray array = obj.getJSONArray(tid);
                    for (int i = 0; i < array.length(); i++) {
                        NetEase netEase = gson.fromJson(array.get(i).toString(), NetEase.class);
                        neteaseNews.add(netEase);
                    }
                    //
                    if (listener != null) {
                        listener.setNewsList(neteaseNews);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.d(TAG, "onCancelled: ");
            }

            @Override
            public void onFinished() {
                Log.d(TAG, "onFinished: ");
            }
        };
        x.http().get(entity,callback);
    }
    public interface OnSuccessListener{
        void setNewsList(List<NetEase> neteaseNews);

    }
}
