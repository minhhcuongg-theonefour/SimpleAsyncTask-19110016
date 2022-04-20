package com.android.progressbar;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Date;

public class MyWorkTask extends AsyncTask<ParamInfo, ProgressInfo, ResultInfo> {

    private final ProgressBar progressBar;
    private final TextView textViewInfo;
    private final TextView textViewNap;
    private final Button buttonStart;


    private final int PROGRESS_MAX;
    private int workCount = 0;
    private long startTimeInMillis;

    public MyWorkTask(ProgressBar progressBar, TextView textViewInfo, TextView textViewNap,
                      Button buttonStart)  {
        this.progressBar = progressBar;
        this.textViewInfo = textViewInfo;
        this.textViewNap = textViewNap;
        this.buttonStart = buttonStart;
        this.PROGRESS_MAX = this.progressBar.getMax();
    }

    @Override
    protected void onPreExecute() {
        this.progressBar.setVisibility(ProgressBar.VISIBLE);
        this.textViewInfo.setText("Start...");
        this.textViewNap.setText("Working...");
        this.buttonStart.setEnabled(false);
        this.startTimeInMillis = new Date().getTime();
    }
    @Override
    protected ResultInfo doInBackground(ParamInfo... params) {
        final int WORK_MAX = 30;

        while (this.workCount < WORK_MAX) {
            SystemClock.sleep(100); // 100 Milliseconds.
            this.workCount++;

            int progress = (this.workCount * PROGRESS_MAX) / WORK_MAX; // Progress value.
            int percent = (progress * 100) / PROGRESS_MAX;
            String info = "(" + percent +"%) - Sleep percent";

            ProgressInfo progressInfo = new ProgressInfo(progress, info);
            this.publishProgress(progressInfo); // Progress ...values
        }
        long finishTimeInMillis = new Date().getTime();
        long workTimeInMillis = finishTimeInMillis - this.startTimeInMillis;
        ResultInfo result = new ResultInfo(true, workTimeInMillis);
        return result;
    }
    @Override
    protected void onProgressUpdate(ProgressInfo... values) { // Progress ...values
        ProgressInfo progressInfo= values[0];

        int progress = progressInfo.getProgress();

        this.progressBar.setProgress(progress);
        this.textViewInfo.setText(progressInfo.getWorkingInfo());
    }

    @Override
    protected void onPostExecute(ResultInfo resultInfo) {
        super.onPostExecute(resultInfo);
        this.buttonStart.setEnabled(true);
    }

    @Override
    protected void onCancelled(ResultInfo resultInfo) {
        super.onCancelled(resultInfo);
        this.buttonStart.setEnabled(true);
    }
}
