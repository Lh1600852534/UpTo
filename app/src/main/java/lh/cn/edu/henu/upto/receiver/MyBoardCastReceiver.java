package lh.cn.edu.henu.upto.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import lh.cn.edu.henu.upto.R;
import lh.cn.edu.henu.upto.UpTo;
import lh.cn.edu.henu.upto.activity.MainActivity;
import lh.cn.edu.henu.upto.activity.NotificationJumpActivity;
import lh.cn.edu.henu.upto.notification.NotificationChannelFactory;

/**
 * by @author lihao
 */
public class MyBoardCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        sendNotification(context);
    }

    /**
     * 发送通知
     *
     * @param context
     */
    private void sendNotification(Context context) {

        Intent intentActivity = new Intent(context, NotificationJumpActivity.class);

        //确保通知渠道存在
        NotificationChannelFactory.isNotificationChannelDelay(context);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, UpTo.NOTIFICATION_CHANNEL_ID_DELAY)
                        .setContentText("Hello World")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("UpTo")
                        .setChannelId(UpTo.NOTIFICATION_CHANNEL_ID_DELAY);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotificationJumpActivity.class);
        stackBuilder.addNextIntent(intentActivity);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());


    }
}
