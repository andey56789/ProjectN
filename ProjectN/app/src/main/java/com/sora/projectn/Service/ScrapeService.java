package com.sora.projectn.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;


import com.sora.projectn.dataservice.TeamDS;
import com.sora.projectn.dataservice.impl.TeamDSImpl;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Sora on 2016/1/11.
 * 爬取NBA官网数据
 */
public class ScrapeService extends Service {

    //关联Service和Activity
    private CrawlerServiceBinder binder = new CrawlerServiceBinder();
    private OnParserCallBack callBack;
    private final int SCRAPE_DATA = 0x01;
    private final int SCRAPE_SUCCESS = 0x02;
    private final int SCRAPE_ERROR = 0x03;
    private final int IO_ERROR = 0x04;

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private CountDownLatch handlerCountDownLatch = new CountDownLatch(2);


    //返回binder 使得Service的引用可以通过返回的IBinder对象得到
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        handler.sendEmptyMessage(SCRAPE_DATA);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SCRAPE_DATA:
                    crawlerData();
                    break;
                case SCRAPE_SUCCESS:
                    //爬取数据完成
                    if (callBack != null){
                        callBack.OnParserComplete(true);
                    }
                    break;
                case SCRAPE_ERROR:
                    Toast.makeText(ScrapeService.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    break;
                case IO_ERROR:
                    Toast.makeText(ScrapeService.this,"无法读取SDCard",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //爬取数据
    //TODO MATCH的日期选择问题 尽可能在SETTING中设置 实现重新获取的功能 目前默认为获取  [20141001-20151231] 的比赛记录
    private void crawlerData() {

        //判断SD卡是否存在 若不存在 发送错误报告
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            handler.sendEmptyMessage(IO_ERROR);
            return;
        }
        //TODO 加入如果第一次运行 爬取数据 否则 调用Sql中的数据的处理

        getTeamList.start();

        getTeamLogo.start();

        getTeamListInfo.start();


    }

    /**
     * 获取球队的基本信息 包含了球队的缩写 名字  建立时间  并将这些数据添加到数据库中
     */
    Thread getTeamList = new Thread(new Runnable() {
        @Override
        public void run() {
            TeamDS teamDS = new TeamDSImpl();
            teamDS.setTeamList(getApplicationContext());
            countDownLatch.countDown();
        }
    });

    /**
     * 获取球队的logo 并将这些数据存储到SDCard
     */
    Thread getTeamLogo = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            TeamBDS teamBDS = new TeamLogo();
//            teamBDS.setTeamLogoToSDCard(getApplicationContext());

            handlerCountDownLatch.countDown();

            try {
                handlerCountDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(SCRAPE_SUCCESS);
        }
    });

    /**
     * 获取球队的基本信息 包含了球队的城市 分区 联盟 等信息
     */
    Thread getTeamListInfo = new Thread(new Runnable() {
        @Override
        public void run() {
            TeamDS teamDS = new TeamDSImpl();
            teamDS.setTeamListInfo(getApplicationContext());
            handlerCountDownLatch.countDown();
        }
    });

    //定义CrawlerServiceBinder
    public class CrawlerServiceBinder extends Binder{
        public ScrapeService getService(){
            return ScrapeService.this;
        }
    }

    //定义接口 为binder访问service服务
    public interface OnParserCallBack{
        public void OnParserComplete(Boolean isScrape);
    }

    public void setCallBack(OnParserCallBack callBack){
        this.callBack = callBack;
    }

    public void removeCallBack(){
        callBack = null;
    }
}
