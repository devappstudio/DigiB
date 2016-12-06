package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.mobile.digibanq.db.ActivationCode;
import dev.mobile.digibanq.db.User;
import info.androidhive.digibanq.R;
import io.realm.Realm;

/**
 * Created by banktech on 7/26/2016.
 */
public class ConfirmCode extends AppCompatActivity {

    Button btn_confirm;
    EditText one,two,three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.confirm_code);

        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        one = (EditText)findViewById(R.id.codeOne);
        two = (EditText)findViewById(R.id.codeTwo);
        three = (EditText)findViewById(R.id.codeThree);

        btn_confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
//                Intent reg = new Intent(ConfirmCode.this, RegDetailsPersonal.class);
//                ConfirmCode.this.startActivity(reg);
                confirm_code();
            }

        });

    }



    void confirm_code()
    {
        Realm realm= Realm.getDefaultInstance();
        String code = realm.where(ActivationCode.class).findFirst().getCode();
        if(code.equalsIgnoreCase(one.getText().toString()+""+two.getText().toString()+""+three.getText().toString()))
        {
            realm.beginTransaction();
            User user = realm.where(User.class).findFirst();
            user.setSmscode("1");
            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();
            Intent reg = new Intent(ConfirmCode.this, BusinessActivity.class);
            ConfirmCode.this.startActivity(reg);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Sorry wrong confirmation code",Toast.LENGTH_LONG).show();
        }
    }
}
