package lh.cn.edu.henu.upto.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import lh.cn.edu.henu.upto.R;
import lh.cn.edu.henu.upto.activity.MainActivity;
import lh.cn.edu.henu.upto.activity.NotificationJumpActivity;

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

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setContentText("Hello World")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("66");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        //stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intentActivity);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());


    }
}
