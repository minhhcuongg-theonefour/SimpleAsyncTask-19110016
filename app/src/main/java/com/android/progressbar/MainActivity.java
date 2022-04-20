package com.android.progressbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textViewInfo;
    private TextView textViewNap;
    private Button buttonStart;
    private MyWorkTask myWorkTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        this.textViewInfo = (TextView) this.findViewById(R.id.textView_info);
        this.textViewNap = (TextView)  this.findViewById(R.id.nap_txt);
        this.buttonStart = (Button) this.findViewById(R.id.start_btn);

        this.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWork();
            }
        });
    }

    private void startWork() {
        this.myWorkTask = new MyWorkTask(this.progressBar,
                this.textViewInfo, this.textViewNap, this.buttonStart);

        ParamInfo param = new ParamInfo("Param 1", "Param 2");

        this.myWorkTask.execute(param);
    }
}