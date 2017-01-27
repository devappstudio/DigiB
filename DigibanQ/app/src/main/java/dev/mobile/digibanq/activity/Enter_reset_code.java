package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.mobile.digibanq.db.ActivationCode;
import dev.mobile.digibanq.db.ConfirmationCode;
import dev.mobile.digibanq.db.User;
import info.androidhive.digibanq.R;
import io.realm.Realm;

/**
 * Created by banktech on 11/24/2016.
 */

public class Enter_reset_code extends AppCompatActivity {

    Button reset;
    TextView back;
    EditText Code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.enter_reset_code);

        reset = (Button)findViewById(R.id.reset_code);
        back = (TextView)findViewById(R.id.back);
        Code = (EditText)findViewById(R.id.recover_email);

        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                confirm_code();

            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent reg = new Intent(Enter_reset_code.this, Recover_pass.class);
                Enter_reset_code.this.startActivity(reg);
            }


        });

    }
    void confirm_code()
    {
        Realm realm= Realm.getDefaultInstance();
        String code = realm.where(ConfirmationCode.class).findFirst().getCode();
        if(code.equalsIgnoreCase(Code.getText().toString()))
        {
            Intent reg = new Intent(Enter_reset_code.this, Reset_password.class);
            reg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            reg.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            reg.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            Enter_reset_code.this.startActivity(reg);
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Sorry wrong confirmation code",Toast.LENGTH_LONG).show();
        }
    }


}
