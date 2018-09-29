package com.example.petya.photogallery;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;


@SuppressLint("NewApi")
public class JobSchedulerService extends JobService {

    private PollTask mCurrentTask;

    @Override
    public boolean onStartJob(JobParameters params) {

        mCurrentTask = new PollTask();
        mCurrentTask.execute(params);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    private class PollTask extends AsyncTask<JobParameters,Void,Void> {
        @Override
        protected Void doInBackground(JobParameters... params) {

            return null;
        }

    }
}
