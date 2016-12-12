package io.codehouse.stocksolution.Services;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import io.realm.Realm;

/**
 * Created by finxl-isaac on 12/10/16.
 */

public class SyncService extends Service {
    Handler mHandler = new Handler();
    @Override
    public IBinder onBind(Intent intent) {



        mHandlerTask.run();

        return null;



    }

    Runnable mHandlerTask = new Runnable()
    {
        Realm realm = Realm.getDefaultInstance();
        @Override
        public void run() {

             mHandler.postDelayed(mHandlerTask, (1000 * 2 * 1));
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("SYNC Service", "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }


    @Override
    public void onCreate() {
        Log.e("SYNC Service", "onCreate");

    }

    @Override
    public void onDestroy() {
    }


}
