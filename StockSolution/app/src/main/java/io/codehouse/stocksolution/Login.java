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
import io.codehouse.stocksolution.db.User;
import io.realm.Realm;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Login extends AppCompatActivity {

    TextView register_tv;
    EditText username,password;
    Button login_btn;
    String Username,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        register_tv = (TextView)findViewById(R.id.register_et);
        username = (EditText)findViewById(R.id.username_et);
        password = (EditText)findViewById(R.id.password_et);
        login_btn = (Button)findViewById(R.id.login_btn);


        register_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Login.this, Register.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_user();
            }
        });

    }

    void login_user()
    {
        Username = username.getText().toString();
        Password = password.getText().toString();
        if(!Username.equalsIgnoreCase("") &&!Password.equalsIgnoreCase(""))
        {
            final ProgressDialog pd  = ProgressDialog.show(Login.this,"Please wait ..."," Please Wait  ...", true);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiLocation.getApi())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiEndpoints endpoints = retrofit.create(ApiEndpoints.class);

            Call<io.codehouse.stocksolution.models.User> login = endpoints.login(Username,Password);
            login.enqueue(new Callback<io.codehouse.stocksolution.models.User>() {
                @Override
                public void onResponse(Response<io.codehouse.stocksolution.models.User> response, Retrofit retrofit) {
                    pd.hide();
                    io.codehouse.stocksolution.models.User user = response.body();

                    try
                    {
                        if(user.getError().equalsIgnoreCase(null))
                        {
                            Toast.makeText(Login.this,user.getError(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Realm realm = Realm.getDefaultInstance();
                            User user1 = new User(1,user.getFull_name(),user.getUsername(),1);
                            realm.beginTransaction();
                            user1.setServer_id(user.getId());
                            user1.setFull_name(user.getFull_name());
                            realm.copyToRealm(user1);
                            realm.commitTransaction();
                            final Intent intent = new Intent(Login.this, Home.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivity(intent);
                            finish();

                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Realm realm = Realm.getDefaultInstance();
                        User user1 = new User(1,user.getName(),user.getUsername(),1);
                        realm.beginTransaction();
                        realm.copyToRealm(user1);
                        realm.commitTransaction();
                        final Intent intent = new Intent(Login.this, Home.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                        finish();

                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    pd.hide();
                    t.printStackTrace();
                    Toast.makeText(Login.this,"Sorry An Error Occured Please Try Again Later",Toast.LENGTH_LONG).show();

                }
            });


        }
        else
        {
            Toast.makeText(getApplicationContext(),"Sorry All Fields Are Required",Toast.LENGTH_LONG).show();
        }


    }

}
