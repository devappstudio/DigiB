package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import info.androidhive.digibanq.R;

/**
 * Created by banktech on 7/12/2016.
 */
public class Register extends AppCompatActivity{

    Button back,cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.register);

        back = (Button)findViewById(R.id.btn_back);
        cont = (Button)findViewById(R.id.btn_cont);


        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform action on click
                Intent signIn = new Intent(Register.this, MainActivity.class);
                Register.this.startActivity(signIn);

            }
        });

        cont.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent reg = new Intent(Register.this, ConfirmCode.class);
                Register.this.startActivity(reg);
            }


        });

    }
}
