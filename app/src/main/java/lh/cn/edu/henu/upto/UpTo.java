package lh.cn.edu.henu.upto;

import android.app.Application;
import android.content.Context;

import lh.cn.edu.henu.upto.designMode.Singleton;

/**
 * Created by LiHao on 2018/7/15.
 * 关于单例模式 查看 https://mp.weixin.qq.com/s/91H5oXJuv7ChXXKyKjNr0w
 */
public class UpTo extends Application {

    private static UpTo mContext;
    public static final String NOTIFICATION_CHANNEL_ID_IMMEDIATELY = "notification_channel_id_immediately";
    public static final String NOTIFICATION_CHANNEL_ID_DELAY = "notification_channel_id_delay";


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getInstance(){
        if(mContext == null){
            mContext = new UpTo();
        }

        return mContext;
    }

}
