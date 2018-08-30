package lh.cn.edu.henu.upto.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {
    private final String TAG = "MyBindService";
    private int count;
    private boolean quit;
    private Thread thread;
    private SimpleBinder simpleBinder = new SimpleBinder();
    
    public MyBindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return simpleBinder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
/*        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!quit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count ++;
                }
            }
        });
        thread.start();*/

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    public int getCount(){
        return count;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        this.quit = true;
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    public class SimpleBinder extends Binder{

        public MyBindService getService(){

            return MyBindService.this;
        }
    }
}
