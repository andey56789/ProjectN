package com.sora.projectn.database.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sora.projectn.database.DBHelper;
import com.sora.projectn.po.MatchPo;
import com.sora.projectn.po.PlayerPo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sora on 2026/2/3.
 *
 * 对match表进行操作
 */
public class MatchDBManager {

    private DBHelper helper;
    private SQLiteDatabase db;

    private static final String TABLE = "match";



    private static final String KEY_DATE = "date";

    private static final String KEY_ABBR1 = "abbr1";
    private static final String KEY_SCORING1 = "scoring1";
    private static final String KEY_PLAYER1 = "player1";
    private static final String KEY_MP1 = "mp1";
    private static final String KEY_FG1 = "fg1";
    private static final String KEY_FGA1 = "fga1";
    private static final String KEY_P31 = "p31";
    private static final String KEY_P3A1 = "p3a1";
    private static final String KEY_FT1 = "ft1";
    private static final String KEY_FTA1 = "fta1";
    private static final String KEY_ORB1 = "orb1";
    private static final String KEY_DRB1 = "drb1";
    private static final String KEY_TRB1 = "trb1";
    private static final String KEY_AST1 = "ast1";
    private static final String KEY_STL1 = "stl1";
    private static final String KEY_BLK1 = "blk1";
    private static final String KEY_TOV1 = "tov1";
    private static final String KEY_PF1 = "pf1";
    private static final String KEY_PTS1 = "pts1";

    private static final String KEY_ABBR2 = "abbr2";
    private static final String KEY_SCORING2 = "scoring2";
    private static final String KEY_PLAYER2 = "player2";
    private static final String KEY_MP2 = "mp2";
    private static final String KEY_FG2 = "fg2";
    private static final String KEY_FGA2 = "fga2";
    private static final String KEY_P32 = "p32";
    private static final String KEY_P3A2 = "p3a2";
    private static final String KEY_FT2 = "ft2";
    private static final String KEY_FTA2 = "fta2";
    private static final String KEY_ORB2 = "orb2";
    private static final String KEY_DRB2 = "drb2";
    private static final String KEY_TRB2 = "trb2";
    private static final String KEY_AST2 = "ast2";
    private static final String KEY_STL2 = "stl2";
    private static final String KEY_BLK2 = "blk2";
    private static final String KEY_TOV2 = "tov2";
    private static final String KEY_PF2 = "pf2";
    private static final String KEY_PTS2 = "pts2";


    public MatchDBManager(Context context) {
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    /**
     * 增加 比赛数据
     * @param po
     */
    public void add(MatchPo po) {

        ContentValues cv = new ContentValues();

        cv.put(KEY_DATE, po.getDate());

        cv.put(KEY_ABBR1, po.getAbbr1());
        cv.put(KEY_SCORING1, po.getScoring1());
        cv.put(KEY_PLAYER1,  po.getPlayer1());
        cv.put(KEY_MP1, po.getMp1());
        cv.put(KEY_FG1, po.getFg1());
        cv.put(KEY_FGA1, po.getFga1());
        cv.put(KEY_P31, po.getP31());
        cv.put(KEY_P3A1, po.getP3a1());
        cv.put(KEY_FT1, po.getFt1());
        cv.put(KEY_FTA1, po.getFta1());
        cv.put(KEY_ORB1, po.getOrb1());
        cv.put(KEY_DRB1, po.getDrb1());
        cv.put(KEY_TRB1, po.getTrb1());
        cv.put(KEY_AST1, po.getAst1());
        cv.put(KEY_STL1, po.getStl1());
        cv.put(KEY_BLK1, po.getBlk1());
        cv.put(KEY_TOV1, po.getTov1());
        cv.put(KEY_PF1, po.getPf1());
        cv.put(KEY_PTS1,  po.getPts1());

        cv.put(KEY_ABBR2, po.getAbbr2());
        cv.put(KEY_SCORING2, po.getScoring2());
        cv.put(KEY_PLAYER2,  po.getPlayer2());
        cv.put(KEY_MP2, po.getMp2());
        cv.put(KEY_FG2, po.getFg2());
        cv.put(KEY_FGA2, po.getFga2());
        cv.put(KEY_P32, po.getP32());
        cv.put(KEY_P3A2, po.getP3a2());
        cv.put(KEY_FT2, po.getFt2());
        cv.put(KEY_FTA2, po.getFta2());
        cv.put(KEY_ORB2, po.getOrb2());
        cv.put(KEY_DRB2, po.getDrb2());
        cv.put(KEY_TRB2, po.getTrb2());
        cv.put(KEY_AST2, po.getAst2());
        cv.put(KEY_STL2, po.getStl2());
        cv.put(KEY_BLK2, po.getBlk2());
        cv.put(KEY_TOV2, po.getTov2());
        cv.put(KEY_PF2, po.getPf2());
        cv.put(KEY_PTS2,  po.getPts2());


        db.insert(TABLE, null, cv);

    }


    /**
     * 查找球员照片网络路径
     * @return List<String>
     */
    public Map<String,String> queryPlayerImg(){
        Map<String,String> map = new HashMap<>();
        Cursor c = queryTheCursor();

//        while (c.moveToNext()){
//
//
//
//        }

        c.close();
        return map;
    }


    /**
     * 查找 球队基本数据 返回带游标的数据集
     * @return Cursor
     */
    public Cursor queryTheCursor(){
        Cursor c = db.rawQuery("SELECT * FROM "+ TABLE,null);
        return c;
    }

    /**
     * 关闭数据库
     */
    public void closeDB(){
        db.close();
    }
}