package lh.cn.edu.henu.upto.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import lh.cn.edu.henu.upto.R;


public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText mEditText;
    private Button mBtnExecute, mBtnCancel;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        initView();
        //ThreadUtil threadUtil = new ThreadUtil();
        //threadUtil.bindContext(this);

    }

    private void initView(){

        mEditText = (EditText)findViewById(R.id.async_task_edit);
        mEditText.setText("60");
        mBtnExecute = (Button)findViewById(R.id.async_task_execute);
        mBtnExecute.setOnClickListener(this);
        mBtnCancel = (Button)findViewById(R.id.async_task_cancel);
        mBtnCancel.setOnClickListener(this);
        mProgressBar = (ProgressBar)findViewById(R.id.async_task_progress_bar);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.async_task_execute:
                ProgressAsyncTask progressAsyncTask = new ProgressAsyncTask();
                progressAsyncTask.execute(Integer.parseInt(mEditText.getText().toString()));
                progressAsyncTask.onCancelled();
                mProgressBar.setMax(Integer.parseInt(mEditText.getText().toString()));
                break;
            case R.id.async_task_cancel:
                break;
            default:
                break;
        }
    }

    private static class ProgressAsyncTask extends AsyncTask<Integer,Integer,String>{

        @Override
        protected String doInBackground(Integer... integers) {
            Log.i("asyncTask", "doInBackground, Thread name: " + Thread.currentThread().getName());
            int val = integers[0];
            for(int i = val; i > 0; i--){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
/*            while (true){
                Log.i("ttss", "doInBackground: doInBackground");
            }*/
            return "gg";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i("asyncTask", "onProgressUpdate, Thread name: " + Thread.currentThread().getName());
            super.onProgressUpdate(values);

            //mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPreExecute() {
            Log.i("asyncTask", "onPreExecute, Thread name: " + Thread.currentThread().getName());
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("asyncTask", "onPostExecute, Thread name: " + Thread.currentThread().getName());
            super.onPostExecute(s);
            Log.i("asyncTask", "onPostExecute, return values: " + s);
        }

        @Override
        protected void onCancelled(String s) {
            Log.i("asyncTask", "onCancelled, Thread name: " + Thread.currentThread().getName());
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            Log.i("asyncTask", "onCancelled, Thread name: " + Thread.currentThread().getName());
            super.onCancelled();
        }
    }
}
