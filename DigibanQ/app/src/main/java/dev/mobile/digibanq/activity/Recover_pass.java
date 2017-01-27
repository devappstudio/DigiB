package dev.mobile.digibanq.activity;

import android.app.ProgressDialog;
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

import java.util.Random;

import dev.mobile.digibanq.api.UserDetails;
import dev.mobile.digibanq.db.ActivationCode;
import dev.mobile.digibanq.db.ConfirmationCode;
import dev.mobile.digibanq.db.User;
import dev.mobile.digibanq.endpoints.ApiEndpoints;
import dev.mobile.digibanq.endpoints.ApiLocation;
import info.androidhive.digibanq.R;
import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by banktech on 11/22/2016.
 */

public class Recover_pass extends AppCompatActivity {

    Button recover;
    TextView back;
    EditText email;
    String Email;
    static String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.recover_password);
        Random r = new Random();
        int i1 = r.nextInt(999 - 100 + 1) + 100;
        code = ""+i1;

        recover = (Button)findViewById(R.id.recover);
        back = (TextView)findViewById(R.id.back);
        email = (EditText)findViewById(R.id.recover_email);


        recover.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform action on click
                recover_password_user();

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



    void recover_password_user()
    {
        Email = email.getText().toString();
        if(!Email.equalsIgnoreCase(""))
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiLocation.getApi())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);

            Call<UserDetails> insert = endpoints.forgot_password(Email,code);
            insert.enqueue(new Callback<UserDetails>() {
                final ProgressDialog pd  = ProgressDialog.show(Recover_pass.this,"Please wait ..."," Please Wait Sending Your Confirmation Code  ...", true);

                @Override
                public void onResponse(Response<UserDetails> response, Retrofit retrofit) {
                    pd.hide();
                    UserDetails userDetails = response.body();
                    if(userDetails.getError().equalsIgnoreCase("N/A"))
                    {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        ConfirmationCode activationCode = new ConfirmationCode(code,Email);
                        realm.copyToRealmOrUpdate(activationCode);
                        realm.commitTransaction();
                        Intent reg = new Intent(Recover_pass.this, Enter_reset_code.class);
                        reg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        reg.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        reg.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        Recover_pass.this.startActivity(reg);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(Recover_pass.this,userDetails.getError(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    pd.hide();

                }
            });


        }
        else
        {
            Toast.makeText(getApplicationContext(),"Sorry All Fields Are Required",Toast.LENGTH_LONG).show();
        }


    }

}
