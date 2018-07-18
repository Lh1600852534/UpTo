package lh.cn.edu.henu.upto.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import lh.cn.edu.henu.upto.UpTo;

/**
 * Created by LiHao on 2018/7/16.
 */
public class NotificationChannelFactory {

    /**
     * 判断 notification_channel_id_immediately 通知渠道是否存在，如果不存在，则创建
     * @param context
     */
    public static void isNotificationChannelImmediate(Context context){

        String channelId = UpTo.NOTIFICATION_CHANNEL_ID_IMMEDIATELY;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            if(notificationManager.getNotificationChannel(channelId) == null){

                String id = UpTo.NOTIFICATION_CHANNEL_ID_IMMEDIATELY;
                CharSequence name = UpTo.NOTIFICATION_CHANNEL_ID_IMMEDIATELY;
                String description = "the notification is immediately send";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                android.app.NotificationChannel mChannel = new android.app.NotificationChannel(id, name, importance);
                mChannel.setDescription(description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                notificationManager.createNotificationChannel(mChannel);
            }
        }
    }

    /**
     * 判断 notification_channel_id_delay 通知渠道是否存在，如果不存在，则创建
     * @param context
     */
    public static void isNotificationChannelDelay(Context context){

        String channelId = UpTo.NOTIFICATION_CHANNEL_ID_DELAY;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            if(notificationManager.getNotificationChannel(channelId) == null){

                String id = UpTo.NOTIFICATION_CHANNEL_ID_DELAY;
                CharSequence name = UpTo.NOTIFICATION_CHANNEL_ID_DELAY;
                String description = "the notification is delay send";
                int importance = NotificationManager.IMPORTANCE_HIGH;

                NotificationChannel mChannel = new NotificationChannel(id, name, importance);
                mChannel.setDescription(description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.BLUE);
                mChannel.enableVibration(false);
                notificationManager.createNotificationChannel(mChannel);
            }
        }
    }
}
