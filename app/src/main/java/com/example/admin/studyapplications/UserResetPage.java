package com.example.admin.studyapplications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserResetPage extends AppCompatActivity {

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reset_page);

        submit = findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         validate(Username.getText().toString(), Password.getText().toString());

                Intent intent = new Intent(UserResetPage.this, LoginPages.class);

                startActivity(intent);
            }
        });
    }
}
