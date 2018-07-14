package lh.cn.edu.henu.upto.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class YourBoardCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Toast.makeText(context,"YourBoardCastReceiver" + action, Toast.LENGTH_SHORT).show();
    }
}
