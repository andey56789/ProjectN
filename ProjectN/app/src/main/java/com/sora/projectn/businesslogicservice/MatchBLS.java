package com.sora.projectn.businesslogicservice;

import android.content.Context;

import com.sora.projectn.po.MatchInfoPo;
import com.sora.projectn.vo.MatchInfoVo;

import java.util.List;

/**
 * Created by Sora on 2016/2/6.
 */
public interface MatchBLS {
    /**
     * 获取记录某一日的所有比赛的表
     *
     * @param list 原始表
     * @return
     */
    public List<MatchInfoVo> getMatchInfoVo(Context context,List<MatchInfoPo> list);
}
