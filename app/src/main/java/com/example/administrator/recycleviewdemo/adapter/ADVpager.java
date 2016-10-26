package com.example.administrator.recycleviewdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.recycleviewdemo.R;
import com.example.administrator.recycleviewdemo.entity.NetEase;

import java.util.List;

import utils.XImageUtil;

/**
 * Created by Administrator on 2016/10/25.
 */

public class ADVpager extends PagerAdapter {
    private List<NetEase.Ad> list;

    public ADVpager(List<NetEase.Ad> list) {
        this.list = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            View view =View .inflate(container.getContext(), R.layout.layout_item_one_head,null);
        TextView mTitile= (TextView) view.findViewById(R.id.tv_title);
        ImageView imageView= (ImageView) view .findViewById(R.id.img_head);
        mTitile.setText(list.get(position%list.size()).title);
        XImageUtil.display(imageView,list.get(position%list.size()).imgsrc);
        container.addView(view );
        return  view ;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
