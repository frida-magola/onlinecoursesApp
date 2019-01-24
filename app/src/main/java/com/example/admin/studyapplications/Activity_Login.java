package com.example.admin.studyapplications;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Activity_Login extends AppCompatActivity {

    RelativeLayout rellay1;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginbtns);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);

        handler.postDelayed(runnable, 3000);

        Button button = findViewById(R.id.userbtn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),LoginPages.class));
           /* if you want to finish the first activity then just call
            finish(); */


            }
        });

        Button button1= findViewById(R.id.brandbtn);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),BrandLoginPages.class));
           /* if you want to finish the first activity then just call
            finish(); */

            }
        });

    }

}
