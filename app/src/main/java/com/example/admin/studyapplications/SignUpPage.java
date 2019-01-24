package com.example.admin.studyapplications;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class SignUpPage extends AppCompatActivity {

    private EditText name;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText confirmPassEditText;
    private Button createAccountBtn;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        updateUI();

    }

    public void updateUI(){

        firstNameEditText = findViewById(R.id.txtFirstName);
        lastNameEditText = findViewById(R.id.txtLastName);
        emailEditText = findViewById(R.id.txtEmail);
        phoneEditText = findViewById(R.id.txtContactNumber);
        passwordEditText = findViewById(R.id.txtPassword);
        confirmPassEditText = findViewById(R.id.txtConfirmPassword);
        createAccountBtn = findViewById(R.id.btnRegister);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";

        awesomeValidation.addValidation(SignUpPage.this,R.id.txtFirstName,"[a-zA-Z\\s]+", R.string.first_nameerr);
        awesomeValidation.addValidation(SignUpPage.this,R.id.txtLastName,"[a-zA-Z\\s]+", R.string.last_nameerr);
        awesomeValidation.addValidation(SignUpPage.this, R.id.txtEmail, android.util.Patterns.EMAIL_ADDRESS, R.string.emailerr);
        awesomeValidation.addValidation(SignUpPage.this,R.id.txtContactNumber,RegexTemplate.TELEPHONE, R.string.phone_numbererr);
        awesomeValidation.addValidation(SignUpPage.this,R.id.txtPassword,regexPassword,R.string.pass_err);
        awesomeValidation.addValidation(SignUpPage.this,R.id.txtConfirmPassword,R.id.txtPassword,R.string.confirm_passworderr);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()){
                    // leading to next screen
                    Intent intent = new Intent(SignUpPage.this,VerifyAccount.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUpPage.this,"Error",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
