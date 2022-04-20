package com.android.progressbar;

public class ResultInfo {
    private boolean completed;
    private long workTimeInMillis;

    public ResultInfo(boolean completed, long workTimeInMillis) {
        this.completed = completed;
        this.workTimeInMillis = workTimeInMillis;
    }

    public boolean isCompleted() {
        return completed;
    }

    public long getWorkTimeInMillis() {
        return workTimeInMillis;
    }
}
