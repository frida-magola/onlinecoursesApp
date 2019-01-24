package com.example.admin.studyapplications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BrandLoginPages extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView forgotPassword;
    private Button Login, SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_login_pages);

        Username = (EditText) findViewById(R.id.txtUsername);
        Password = (EditText) findViewById(R.id.txtPassword);
        forgotPassword = (TextView) findViewById(R.id.btnforgotPass);
        SignUp = (Button) findViewById(R.id.btnsignUp);
        Login = (Button) findViewById(R.id.btnLogin);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         validate(Username.getText().toString(), Password.getText().toString());

                Intent intent = new Intent(BrandLoginPages.this, NavigationOfBrand.class);

                startActivity(intent);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         validate(Username.getText().toString(), Password.getText().toString());

                Intent intent = new Intent(BrandLoginPages.this, BrandSignUp.class);

                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         validate(Username.getText().toString(), Password.getText().toString());

                Intent intent = new Intent(BrandLoginPages.this, VerifyAccount.class);

                startActivity(intent);
            }
        });

    }
}
