package lh.cn.edu.henu.upto.keepAlive;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import lh.cn.edu.henu.upto.UpTo;

public class GrayService extends Service {
    public GrayService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       if(Build.VERSION.SDK_INT < 18){
           startForeground(UpTo.GRAY_SERVICE_ID, new Notification());
       }else {
           Log.i("ttss", "onStartCommand: grayService");
           startForeground(UpTo.GRAY_SERVICE_ID, new Notification());
           Intent innerIntent = new Intent(this, GrayInnerService.class);
           startService(innerIntent);
       }

        return super.onStartCommand(intent, flags, startId);
    }

    public static class GrayInnerService extends Service {

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i("ttss", "onStartCommand: GrayInnerService");
            startForeground(UpTo.GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}
