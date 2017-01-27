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
 * Created by banktech on 11/24/2016.
 */

public class Reset_password extends AppCompatActivity {

    Button reset;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.reset_password);

        reset = (Button)findViewById(R.id.reset_password);
        back = (TextView)findViewById(R.id.back);


        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform action on click


            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent reg = new Intent(Reset_password.this, Enter_reset_code.class);
                Reset_password.this.startActivity(reg);
            }


        });

    }
}

