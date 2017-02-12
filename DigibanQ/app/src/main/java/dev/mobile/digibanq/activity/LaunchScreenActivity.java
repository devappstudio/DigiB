package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import dev.mobile.digibanq.db.ConfirmationCode;
import dev.mobile.digibanq.db.User;
import info.androidhive.digibanq.R;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;


public class LaunchScreenActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        Transparent Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_launch_screen);

        new BackgroundTask().execute();
    }


    private class BackgroundTask extends AsyncTask {
        Intent intent;
        Realm realm = Realm.getDefaultInstance();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            RealmResults<User> user = realm.where(User.class).findAll();
            intent = new Intent(LaunchScreenActivity.this, BusinessActivity.class);
            if(user.isEmpty())
            intent = new Intent(LaunchScreenActivity.this, MainActivity.class);
            else
            {
                RealmResults<ConfirmationCode> confirmationCode  = realm.where(ConfirmationCode.class).findAll();
                if(confirmationCode.isEmpty())
                {
                    intent = new Intent(LaunchScreenActivity.this, ConfirmCode.class);
                }
                else
                {
                    intent = new Intent(LaunchScreenActivity.this, BusinessActivity.class);
                }
            }

        }

        @Override
        protected Object doInBackground(Object[] params) {

            /*  Use this method to load background
            * data that your app needs. */

            try {
                Thread.sleep(SPLASH_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            Pass your loaded data here using Intent
//            intent.putExtra("data_key", "");
            startActivity(intent);
            finish();
        }
    }
}
