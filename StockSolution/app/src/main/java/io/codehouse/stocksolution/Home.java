package io.codehouse.stocksolution;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import io.codehouse.stocksolution.db.User;
import io.realm.Realm;

public class Home extends AppCompatActivity {
    Button logout,stores,settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = (Button)findViewById(R.id.logout);
        stores = (Button)findViewById(R.id.storesbtn);
        settings = (Button)findViewById(R.id.accountbtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.deleteAll();
                realm.commitTransaction();
                final Intent intent = new Intent(Home.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();

            }
        });
        stores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(Home.this, Stores.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog to edit details
                final Dialog dialog = new Dialog(Home.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.edit_details_dialog);
                EditText fullname = (EditText) dialog.findViewById(R.id.full_name_et);
                EditText username = (EditText) dialog.findViewById((R.id.user_name_et));
                Button change = (Button)dialog.findViewById(R.id.change_details_btn);
                Realm realm = Realm.getDefaultInstance();
                User user = realm.where(User.class).findFirst();

                fullname.setText(user.getFull_name());
                username.setText(user.getUsername());

                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                dialog.show();

            }
        });

    }
}
