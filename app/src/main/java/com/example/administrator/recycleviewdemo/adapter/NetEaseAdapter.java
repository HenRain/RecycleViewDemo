package com.example.administrator.recycleviewdemo.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.recycleviewdemo.R;
import com.example.administrator.recycleviewdemo.entity.NetEase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.XImageUtil;

/**
 * Created by Administrator on 2016/10/25.
 */

public class NetEaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<NetEase> dataList;
    private static final int VIEW_VIEWPAGER = 153;
    private static final int VIEW_LONG_IMAGE = 168;
    private static final int VIEW_ONE_IMAGE = 160;
    private static final int VIEW_THREE_IMAGE = 429;
    private static final int VIEW_FOOTER = 552;

    public NetEaseAdapter(List<NetEase> dataList) {

        this.dataList = dataList;
    }

    public List<NetEase> getDataList() {
        return dataList;
    }

    public void setDataList(List<NetEase> dataList) {
        this.dataList = dataList;
    }
    public void addDataList(List<NetEase> list) {
        if (list == null) {
            Log.d("addDataList", "addDataList: 集合不能为空");
            return;
        }
        dataList.addAll(list);
    }

    public void addData(NetEase netEase) {
        dataList.add(netEase);
    }

    public void addData(int position, NetEase netEase) {
        dataList.add(position, netEase);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itenmView = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case VIEW_VIEWPAGER:
                itenmView = View.inflate(parent.getContext(), R.layout.layout_item_vp, null);
                holder = new ViewPagerHolder(itenmView);
                break;
            case VIEW_LONG_IMAGE:
                itenmView = View.inflate(parent.getContext(), R.layout.layout_item_one_head, null);
                holder = new LongImageHolder(itenmView);
                break;
            case VIEW_ONE_IMAGE:
                itenmView = View.inflate(parent.getContext(), R.layout.layout_item_one_img, null);
                holder = new OneImageHolder(itenmView);
                break;
            case VIEW_THREE_IMAGE:
                itenmView = View.inflate(parent.getContext(), R.layout.layout_item_three_img, null);
                holder = new ThreeImageHolder(itenmView);
                break;
            case VIEW_FOOTER:
                itenmView = View.inflate(parent.getContext(), R.layout.footer, null);
                holder = new FooterHolder(itenmView);
                break;
            default:
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OneImageHolder) {
            initOneImageView((OneImageHolder) holder, dataList.get(position));
        } else if (holder instanceof ViewPagerHolder) {
            initViewPagerView((ViewPagerHolder) holder, dataList.get(position));
        } else if (holder instanceof LongImageHolder) {
            initLongImageView((LongImageHolder) holder, dataList.get(position));
        } else if (holder instanceof ThreeImageHolder) {
            initThreeImageView((ThreeImageHolder) holder, dataList.get(position));
        } else if (holder instanceof FooterHolder) {
            initFooterView((FooterHolder) holder);
        } else {

        }
    }



    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return dataList.get(position).ads == null ? VIEW_LONG_IMAGE : VIEW_VIEWPAGER;
        } else if (position < dataList.size()) {
            return dataList.get(position).imgextra == null ? VIEW_ONE_IMAGE : VIEW_THREE_IMAGE;
        } else {
            return VIEW_FOOTER;
        }
    }

    private void initOneImageView(OneImageHolder h, NetEase netEase) {
        XImageUtil.display(h.mImgLeft, netEase.imgsrc);
        h.mTvTitle.setText(netEase.title);
        h.mTvFollow.setText(netEase.replyCount + "");
    }

    public static final int FOOTER_IDLE = 874;
    public static final int FOOTER_PULLING = 483;
    public static final int FOOTER_PULL_FINISHED = 306;
    public static final int FOOTER_PULL_NO_DATA = 147;
    private int currentState;
    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }


    private void initFooterView(FooterHolder holder) {
        switch (currentState) {
            case FOOTER_IDLE:
                holder.mProgressBar1.setVisibility(View.INVISIBLE);
                break;
            case FOOTER_PULL_FINISHED:
            break;
            case FOOTER_PULL_NO_DATA:
                holder .mTextView1.setText("已经没有更多的数据了");
                holder.mProgressBar1.setVisibility(View.INVISIBLE);
            break;
            case FOOTER_PULLING:
                holder .mTextView1.setText("正在加载请稍后。。。。。。");
                holder.mProgressBar1.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void initThreeImageView(ThreeImageHolder holder, NetEase netEase) {
        holder.mTvTitle.setText(netEase.title);
        holder.mTvFollow.setText(netEase.replyCount + "");
        XImageUtil.display(holder.mImg1, netEase.imgsrc);
        XImageUtil.display(holder.mImg2, netEase.imgextra.get(0).imgsrc);
        XImageUtil.display(holder.mImg3, netEase.imgextra.get(1).imgsrc);

    }

    private void initLongImageView(LongImageHolder holder, NetEase netEase) {
        XImageUtil.display(holder.mImgHead, netEase.imgsrc);
        holder.mTvTitle.setText(netEase.title);
    }

    private void initViewPagerView(final ViewPagerHolder holder, NetEase netEase) {
        ADVpager adapter = new ADVpager(netEase.ads);
        holder.mVpager.setAdapter(adapter);
        if (holder.mLlLayout.getChildCount() == 0) {
            for (int i = 0; i < netEase.ads.size(); i++) {
                ImageView img = new ImageView(holder.mLlLayout.getContext());
                img.setImageResource(R.drawable.adware_style_default);
                holder.mLlLayout.addView(img);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img.getLayoutParams();
                layoutParams.leftMargin = 5;
                layoutParams.rightMargin = 5;
            }
        }
        ((ImageView) (holder.mLlLayout.getChildAt(0))).setImageResource(R.drawable.adware_style_selected);
        holder.mVpager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % netEase.ads.size()));
        holder.mVpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < holder.mLlLayout.getChildCount(); i++) {
                    ImageView img = (ImageView) holder.mLlLayout.getChildAt(i);
                    img.setImageResource(R.drawable.adware_style_default);
                }
                ImageView img = (ImageView) holder.mLlLayout.getChildAt(position % holder.mLlLayout.getChildCount());
                img.setImageResource(R.drawable.adware_style_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });


    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public static class OneImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_left)
        ImageView mImgLeft;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_follow)
        TextView mTvFollow;

        public OneImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ThreeImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.img1)
        ImageView mImg1;
        @BindView(R.id.img2)
        ImageView mImg2;
        @BindView(R.id.img3)
        ImageView mImg3;
        @BindView(R.id.tv_follow)
        TextView mTvFollow;

        public ThreeImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class LongImageHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_head)
        ImageView mImgHead;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public LongImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ViewPagerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vpager)
        ViewPager mVpager;
        @BindView(R.id.ll_layout)
        LinearLayout mLlLayout;

        public ViewPagerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class FooterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progressBar1)
        ProgressBar mProgressBar1;
        @BindView(R.id.textView1)
        TextView mTextView1;
        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
