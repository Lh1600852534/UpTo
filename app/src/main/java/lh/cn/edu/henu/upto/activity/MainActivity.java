package lh.cn.edu.henu.upto.activity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Calendar;

import lh.cn.edu.henu.upto.R;
import lh.cn.edu.henu.upto.UpTo;
import lh.cn.edu.henu.upto.notification.NotificationChannelFactory;
import lh.cn.edu.henu.upto.receiver.MyBoardCastReceiver;
import lh.cn.edu.henu.upto.service.MyBindService;
import lh.cn.edu.henu.upto.service.MyIntentService;
import lh.cn.edu.henu.upto.util.ThreadUtil;

/**
 * by @author lihao
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendNotification;
    private Button sendBoardCast;
    private Button startAsyncTask;
    private Button btnBindService;
    private Button btnUnBindService;
    private Button testBindService;
    private Button testBtn;
    private ServiceConnection serviceConnection;
    private MyBindService myBindService;


    ThreadUtil threadUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        intServiceConn();
        threadUtil = new ThreadUtil();
    }

    @Override
    protected void onResume() {
        super.onResume();
        threadUtil = null;
    }

    /**
     *
     */
    private void intServiceConn() {

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("MyBindService", "onServiceConnected: ");
                MyBindService.SimpleBinder simpleBinder = (MyBindService.SimpleBinder)service;
                myBindService = simpleBinder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("MyBindService", "onServiceDisconnected: ");

            }
        };
    }

    private void initView() {
        sendNotification = (Button) findViewById(R.id.send_notification);
        sendNotification.setOnClickListener(this);
        sendBoardCast = (Button) findViewById(R.id.send_boardCast);
        sendBoardCast.setOnClickListener(this);
        startAsyncTask = (Button)findViewById(R.id.start_asyncTask);
        startAsyncTask.setOnClickListener(this);
        btnBindService = (Button)findViewById(R.id.start_bind_service);
        btnBindService.setOnClickListener(this);
        btnUnBindService = (Button)findViewById(R.id.unbind_service);
        btnUnBindService.setOnClickListener(this);
        testBindService = (Button)findViewById(R.id.test_bind_service);
        testBindService.setOnClickListener(this);
        testBtn = (Button)findViewById(R.id.btn_test);
        testBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notification:
                sendNotification();
                break;
            case R.id.send_boardCast:
                sendBoardCast();
                break;
            case R.id.start_asyncTask:
                Intent intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.start_bind_service:
                Intent bindIntent = new Intent(this, MyBindService.class);
                bindService(bindIntent, serviceConnection, Service.BIND_AUTO_CREATE);
                //startService(bindIntent);
                break;
            case R.id.unbind_service:
                if(myBindService != null){
                    myBindService = null;
                    unbindService(serviceConnection);
                }
                break;
            case R.id.test_bind_service:
                if(myBindService != null){
                    Toast.makeText(this, myBindService.getCount() + "", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_test:
                //new testThread().start();
                Intent intent1 = new Intent(this, MyIntentService.class); //测试IntentService
                startService(intent1);
            default:
                break;
        }
    }


    /**
     * 测试Handler
     */
    class testThread extends Thread{

        @Override
        public void run() {
            Log.i("ttssk", "run:testThread " + Thread.currentThread().getName());
            ThreadUtil.runInUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("ttssk", "run:mainThread " + Thread.currentThread().getName());
                    testBindService.setText("dfhdfhda111111");
                }
            });
            ThreadUtil.handler.post(new Runnable() {
                @Override
                public void run() {
                    testBindService.setText("dfhdfhda");
                    Message message = new Message();
                    new Handler().dispatchMessage(message);
                }
            });

/*            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    testBindService.setText("tt");
                }
            });*/
        }
    }

    /**
     * 发送广播
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void sendBoardCast() {

        Intent intentBoardCast = new Intent(this, MyBoardCastReceiver.class);
        PendingIntent pendingIntentBoardCast = PendingIntent.getBroadcast(this, 0,
                intentBoardCast, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar mCalendar = Calendar.getInstance();
        Toast.makeText(this,"",Toast.LENGTH_LONG).show();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.add(Calendar.MILLISECOND, 600000);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntentBoardCast);

    }

    /**
     * 发送通知
     *
     */
    private void sendNotification() {

        //确保通知渠道存在
        NotificationChannelFactory.isNotificationChannelImmediate(this);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, UpTo.NOTIFICATION_CHANNEL_ID_IMMEDIATELY)
                        .setContentText("Hello World")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("UpTo")
                        .setChannelId(UpTo.NOTIFICATION_CHANNEL_ID_IMMEDIATELY);

        //添加返回栈，使按返回键后，不会退出，而是跳转到主界面
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationJumpActivity.class);
        Intent intentActivity = new Intent(this, NotificationJumpActivity.class);
        stackBuilder.addNextIntent(intentActivity);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

    }







}
