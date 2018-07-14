package lh.cn.edu.henu.upto.activity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

import lh.cn.edu.henu.upto.R;
import lh.cn.edu.henu.upto.receiver.MyBoardCastReceiver;

/**
 * by @author lihao
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendNotification;
    private Button sendBoardCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        sendNotification = (Button) findViewById(R.id.send_notification);
        sendNotification.setOnClickListener(this);
        sendBoardCast = (Button) findViewById(R.id.send_boardCast);
        sendBoardCast.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notification:
                sendBoardCast();
                break;
            case R.id.send_boardCast:
                sendBoardCast();
                break;
            default:
                break;
        }
    }



    /**
     * 发送通知
     */
    private void sendBoardCast() {

        Intent intentBoardCast = new Intent(this, MyBoardCastReceiver.class);
        PendingIntent pendingIntentBoardCast = PendingIntent.getBroadcast(this, 0,
                intentBoardCast, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.add(Calendar.MILLISECOND, 10000);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pendingIntentBoardCast);

    }

}
