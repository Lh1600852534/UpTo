package lh.cn.edu.henu.upto.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import lh.cn.edu.henu.upto.R;

public class ClientActivity extends AppCompatActivity {

    private BookManger bookManger;
    private boolean isConnection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Button button = (Button)findViewById(R.id.btn_aidl);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptToBindService();
            }
        });
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            bookManger = Stub.asInterface(service);
            if(bookManger != null){
                try {
                    List<Book> books = bookManger.getBookList();
                    Log.i("aidlClient", "onServiceConnected: " + books.toString());
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnection = false;
        }
    };

    private void attemptToBindService(){

        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("lh.cn.edu.upto.remote");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        if(bookManger != null){
            try {
                List<Book> books = bookManger.getBookList();
                Log.i("aidlClient", "onServiceConnected: " + books.toString());
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!isConnection){
            attemptToBindService();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!isConnection){
            unbindService(serviceConnection);
        }
    }
}
