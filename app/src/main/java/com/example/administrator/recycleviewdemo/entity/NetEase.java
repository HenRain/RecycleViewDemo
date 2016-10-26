package com.example.administrator.recycleviewdemo.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */

public class NetEase {
    public NetEase(List<Ad> ads, String alias, String boardid, String cid, String digest, String docid, String ename, int hasAD, boolean hasCover, int hasHead, boolean hasIcon, int hasImg, List<ImgExtra> imgextra, String imgsrc, int imgsum, String lmodify, int order, String photosetID, String postid, int priority, String ptime, int replyCount, String skipID, String skipType, String source, String template, String title, String tname, String votecount) {
        this.ads = ads;
        this.alias = alias;
        this.boardid = boardid;
        this.cid = cid;
        this.digest = digest;
        this.docid = docid;
        this.ename = ename;
        this.hasAD = hasAD;
        this.hasCover = hasCover;
        this.hasHead = hasHead;
        this.hasIcon = hasIcon;
        this.hasImg = hasImg;
        this.imgextra = imgextra;
        this.imgsrc = imgsrc;
        this.imgsum = imgsum;
        this.lmodify = lmodify;
        this.order = order;
        this.photosetID = photosetID;
        this.postid = postid;
        this.priority = priority;
        this.ptime = ptime;
        this.replyCount = replyCount;
        this.skipID = skipID;
        this.skipType = skipType;
        this.source = source;
        this.template = template;
        this.title = title;
        this.tname = tname;
        this.votecount = votecount;
    }

    @Override
    public String toString() {
        return "NetEase{" +
                "ads=" + ads +
                ", alias='" + alias + '\'' +
                ", boardid='" + boardid + '\'' +
                ", cid='" + cid + '\'' +
                ", digest='" + digest + '\'' +
                ", docid='" + docid + '\'' +
                ", ename='" + ename + '\'' +
                ", hasAD=" + hasAD +
                ", hasCover=" + hasCover +
                ", hasHead=" + hasHead +
                ", hasIcon=" + hasIcon +
                ", hasImg=" + hasImg +
                ", imgextra=" + imgextra +
                ", imgsrc='" + imgsrc + '\'' +
                ", imgsum=" + imgsum +
                ", lmodify='" + lmodify + '\'' +
                ", order=" + order +
                ", photosetID='" + photosetID + '\'' +
                ", postid='" + postid + '\'' +
                ", priority=" + priority +
                ", ptime='" + ptime + '\'' +
                ", replyCount=" + replyCount +
                ", skipID='" + skipID + '\'' +
                ", skipType='" + skipType + '\'' +
                ", source='" + source + '\'' +
                ", template='" + template + '\'' +
                ", title='" + title + '\'' +
                ", tname='" + tname + '\'' +
                ", votecount='" + votecount + '\'' +
                '}';
    }

    public List<NetEase.Ad> ads;
    public String alias;
    public String boardid;
    public String cid;
    public String digest;
    public String docid;
    public String ename;
    public int hasAD;
    public boolean hasCover;
    public int hasHead;
    public boolean hasIcon;
    public int hasImg;
    public List<ImgExtra> imgextra;
    public String imgsrc;
    public int imgsum;
    public String lmodify;
    public int order;
    public String photosetID;
    public String postid;
    public int priority;
    public String ptime;
    public int replyCount;
    public String skipID;
    public String skipType;
    public String source;
    public String template;
    public String title;
    public String tname, votecount;

    public class Ad {
        public String imgsrc;
        public String subtitle;
        public String tag;
        public String title;
        public String url;

        public Ad(String imgsrc, String subtitle, String tag, String title, String url) {
            this.imgsrc = imgsrc;
            this.subtitle = subtitle;
            this.tag = tag;
            this.title = title;
            this.url = url;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "imgsrc='" + imgsrc + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", tag='" + tag + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public class ImgExtra {
        public String imgsrc;

        @Override
        public String toString() {
            return "ImgExtra{" +
                    "imgsrc='" + imgsrc + '\'' +
                    '}';
        }

        public ImgExtra(String imgsrc) {
            this.imgsrc = imgsrc;
        }
    }
}
