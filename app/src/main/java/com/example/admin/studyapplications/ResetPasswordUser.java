package com.example.admin.studyapplications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResetPasswordUser extends AppCompatActivity {

    private Button verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_user);

        verify = (Button) findViewById(R.id.btnSearch);


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         validate(Username.getText().toString(), Password.getText().toString());

                Intent intent = new Intent(ResetPasswordUser.this, VerifyAccount.class);

                startActivity(intent);
            }
        });

    }
}
