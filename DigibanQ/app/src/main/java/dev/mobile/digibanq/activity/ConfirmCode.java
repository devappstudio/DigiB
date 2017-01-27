package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import info.androidhive.digibanq.R;

/**
 * Created by banktech on 7/26/2016.
 */
public class ConfirmCode extends AppCompatActivity {

    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.confirm_code);

        btn_confirm = (Button)findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent reg = new Intent(ConfirmCode.this, RegDetailsPersonal.class);
                ConfirmCode.this.startActivity(reg);
            }


        });

    }
}
