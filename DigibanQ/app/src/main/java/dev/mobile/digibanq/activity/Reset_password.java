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
 * Created by banktech on 11/24/2016.
 */

public class Reset_password extends AppCompatActivity {

    Button reset;
    TextView back;
    EditText password,confirmation_password;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.reset_password);

        reset = (Button)findViewById(R.id.reset_password);
        back = (TextView)findViewById(R.id.back);
        password = (EditText)findViewById(R.id.new_password);
        confirmation_password = (EditText)findViewById(R.id.new_password_two);


        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Perform action on click
                reset_user();
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


    void reset_user()
    {
        Password = password.getText().toString();
        if(!Password.equalsIgnoreCase(""))
        {
            if(Password.equalsIgnoreCase(confirmation_password.getText().toString()))
            {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiLocation.getApi())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);
                Realm realm = Realm.getDefaultInstance();
                ConfirmationCode confirmationCode = realm.where(ConfirmationCode.class).findFirst();

                Call<UserDetails> insert = endpoints.new_password(confirmationCode.getEmail(),Password);
                insert.enqueue(new Callback<UserDetails>() {
                    final ProgressDialog pd  = ProgressDialog.show(Reset_password.this,"Please wait ..."," Please Wait Updating Details  ...", true);

                    @Override
                    public void onResponse(Response<UserDetails> response, Retrofit retrofit) {
                        pd.hide();
                        UserDetails userDetails = response.body();
                        if(userDetails.getError().equalsIgnoreCase("N/A"))
                        {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            User user = new User();
                            user.setId(1);
                            user.setSmscode("1");
                            user.setFullname(userDetails.getFull_name());
                            user.setPhone(userDetails.getPhone());
                            user.setEmail(userDetails.getEmail());
                            user.setUuid(userDetails.getUnique_id());
                            realm.copyToRealmOrUpdate(user);
                            realm.commitTransaction();
                            Intent reg = new Intent(Reset_password.this, BusinessActivity.class);
                            reg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            reg.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            reg.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            Reset_password.this.startActivity(reg);
                            finish();

                        }
                        else
                        {
                            Toast.makeText(Reset_password.this,userDetails.getError(),Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(),"Please Confirm Your Password",Toast.LENGTH_LONG).show();

            }

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Sorry All Fields Are Required",Toast.LENGTH_LONG).show();
        }


    }

}

