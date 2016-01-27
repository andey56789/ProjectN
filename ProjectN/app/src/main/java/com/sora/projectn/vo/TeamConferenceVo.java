package com.sora.projectn.vo;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Map;

/**
 * Created by Sora on 2016/1/25.
 *
 *
 * TeamListActivity调用数据
 * 一个分区的球队信息
 *
 * conference   球队分区
 * sNameList    球队缩略名列表
 * logoList     球队图标列表
 */
public class TeamConferenceVo {

    private String conference;
    private List<String> sNameList;
    private List<Bitmap> logoList;

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public List<Bitmap> getLogoList() {
        return logoList;
    }

    public void setLogoList(List<Bitmap> logoList) {
        this.logoList = logoList;
    }

    public List<String> getsNameList() {
        return sNameList;
    }

    public void setsNameList(List<String> sNameList) {
        this.sNameList = sNameList;
    }
}
