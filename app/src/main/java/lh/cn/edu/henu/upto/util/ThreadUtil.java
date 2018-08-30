package lh.cn.edu.henu.upto.util;


import android.os.Handler;

public class ThreadUtil {


    public static void runInSubThread(Runnable r){
        new Thread(r).start();
    }

    public static Handler handler = new Handler();

    public static void runInUiThread(Runnable r){

       handler.post(r);
    }

}
