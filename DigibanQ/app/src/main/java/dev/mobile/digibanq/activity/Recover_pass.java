package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import info.androidhive.digibanq.R;

/**
 * Created by banktech on 11/22/2016.
 */

public class Recover_pass extends AppCompatActivity {

    Button recover;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.recover_password);

        recover = (Button)findViewById(R.id.recover);
        back = (TextView)findViewById(R.id.back);


        recover.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform action on click


            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent reg = new Intent(Recover_pass.this, MainActivity.class);
                Recover_pass.this.startActivity(reg);
            }


        });

    }
}
