package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import info.androidhive.digibanq.R;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView help, signup;

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
        setContentView(R.layout.sign_in);

        login = (Button)findViewById(R.id.btn_signin);
        signup =  (TextView) findViewById(R.id.reg);


        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform action on click
                Intent signIn = new Intent(MainActivity.this, BusinessActivity.class);
                MainActivity.this.startActivity(signIn);

            }
        });

        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent reg = new Intent(MainActivity.this, LeanActivity.class);
                MainActivity.this.startActivity(reg);
            }


        });



    }
}
