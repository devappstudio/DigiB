package io.codehouse.stocksolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.codehouse.stocksolution.api.ApiEndpoints;
import io.codehouse.stocksolution.api.ApiLocation;
import io.codehouse.stocksolution.models.User;
import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Register extends AppCompatActivity {
    String Fullname,Username,Password,ConfirmPassword;
    EditText fullname,username,password,confirm_password;
    Button register_btn;
    TextView login_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login_tv = (TextView)findViewById(R.id.login_tv);
        register_btn = (Button)findViewById(R.id.register_btn);
        fullname = (EditText)findViewById(R.id.fullname_et);
        username = (EditText)findViewById(R.id.username_et);
        password = (EditText)findViewById(R.id.password_et);
        confirm_password= (EditText)findViewById(R.id.confirm_password_et);

        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Register.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();

            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }


    void register()
    {
        Username = username.getText().toString();
        Password = password.getText().toString();
        Fullname = fullname.getText().toString();
        ConfirmPassword = confirm_password.getText().toString();
        if(!ConfirmPassword.equalsIgnoreCase(Password))
        {
            Toast.makeText(getApplicationContext(),"Please Confirm Your Password",Toast.LENGTH_LONG).show();

        }
        else
        {
            if(!Username.equalsIgnoreCase("") &&!Password.equalsIgnoreCase("") && !Fullname.equalsIgnoreCase(""))
            {
                final ProgressDialog pd  = ProgressDialog.show(Register.this,"Please wait ..."," Please Wait  ...", true);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiLocation.getApi())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);

                Call<User> register = endpoints.register(Fullname,Username,Password);
                register.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        pd.hide();
                        try
                        {
                            io.codehouse.stocksolution.models.User user = response.body();
                            Realm realm = Realm.getDefaultInstance();
                            io.codehouse.stocksolution.db.User user1 = new io.codehouse.stocksolution.db.User(1,user.getName(),user.getUsername(),1);
                            realm.beginTransaction();
                            user1.setServer_id(user.getId());
                            realm.copyToRealm(user1);
                            realm.commitTransaction();
                            final Intent intent = new Intent(Register.this, Home.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                            finish();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(Throwable t) {
                        pd.hide();
                        t.printStackTrace();
                        Toast.makeText(Register.this,"Sorry An Error Occured Please Try Again Later",Toast.LENGTH_LONG).show();

                    }
                });


            }
            else
            {
                Toast.makeText(getApplicationContext(),"Sorry All Fields Are Required",Toast.LENGTH_LONG).show();
            }


        }

    }

}
