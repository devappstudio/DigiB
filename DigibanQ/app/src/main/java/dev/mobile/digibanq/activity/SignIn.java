package dev.mobile.digibanq.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.mobile.digibanq.api.UserDetails;
import dev.mobile.digibanq.db.ActivationCode;
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
 * Created by banktech on 7/7/2016.
 */
public class SignIn extends Activity{

    TextView help, register;
    Button signIn;
    Intent auth;
    EditText password,phone;
    String Phone,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.sign_in);
        signIn = (Button)findViewById(R.id.btn_signin);
        password = (EditText)findViewById(R.id.login_password_et);
        phone = (EditText)findViewById(R.id.login_phone_et);



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  login_user();
            }
        });

    }



    void login_user()
    {
        Password = password.getText().toString();
        Phone = phone.getText().toString();
        if(!Password.equalsIgnoreCase("") &&!Phone.equalsIgnoreCase(""))
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiLocation.getApi())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);

            Call<UserDetails> insert = endpoints.login(Phone,Password);
            insert.enqueue(new Callback<UserDetails>() {
                final ProgressDialog pd  = ProgressDialog.show(SignIn.this,"Please wait ..."," Please Wait Procedding Your Details  ...", true);

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
                        user.setSmscode("0");
                        user.setFullname(userDetails.getFull_name());
                        user.setPhone(Phone);
                        user.setEmail(userDetails.getEmail());
                        realm.copyToRealmOrUpdate(user);
                        realm.commitTransaction();
                        Intent reg = new Intent(SignIn.this, BusinessActivity.class);
                        reg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        reg.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        reg.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        PrefManager prefManager = new PrefManager(getApplicationContext());
                        prefManager.setFirstTimeLaunch(true);
                        startActivity(reg);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(SignIn.this,userDetails.getError(),Toast.LENGTH_LONG).show();
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
