package dev.mobile.digibanq.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

import info.androidhive.digibanq.R;

/**
 * Created by banktech on 7/28/2016.
 */
public class RegDocuments extends AppCompatActivity{

    ImageView upload_profile;
    Button back, next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.reg_documents);
        upload_profile = (ImageView)findViewById(R.id.upload_profile);
        back = (Button)findViewById(R.id.btn_back);
        next = (Button)findViewById(R.id.btn_cont);

        upload_profile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent upload = new Intent();
                upload.setType("image/*");
                upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(upload,"Choose Picture"),1);
            }


        });

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent back = new Intent(RegDocuments.this, RegDetailsEmployment.class);
                RegDocuments.this.startActivity(back);
            }


        });

        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Perform action on click
                Intent next = new Intent(RegDocuments.this, DigiPlanner.class);
                RegDocuments.this.startActivity(next);
            }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode==RESULT_CANCELED){
            //action cancelled
        }
        if (requestCode==RESULT_OK){
            Uri selectedimg = data.getData();
            try {
                upload_profile.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
