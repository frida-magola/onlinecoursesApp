package com.example.admin.studyapplications;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

public class VerifyAccount extends AppCompatActivity {

    private static final int REQUEST_CODE = 999;

    Button btnPhone,btnEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);

        btnPhone = (Button)findViewById(R.id.userbtn);
        btnEmail = (Button)findViewById(R.id.brandbtn);

        btnEmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startLoginPage(LoginType.EMAIL);
            }
        });

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginPage(LoginType.PHONE);
            }
        });
    }

    private void startLoginPage(LoginType loginType)
    {
        if(loginType == LoginType.EMAIL)
        {
            Intent intent = new Intent(this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.EMAIL,
                    AccountKitActivity.ResponseType.TOKEN);
            intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
            startActivityForResult(intent, REQUEST_CODE);
        }else if(loginType == LoginType.PHONE)
        {
            Intent intent = new Intent(this, AccountKitActivity.class);
            AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder = new AccountKitConfiguration.AccountKitConfigurationBuilder(LoginType.PHONE,
                    AccountKitActivity.ResponseType.TOKEN);
            intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE)
        {
            AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if(result.getError() != null)
            {
                Toast.makeText(this,""+result.getError().getErrorType().getMessage(),Toast.LENGTH_SHORT).show();
                return;
            }else if(result.wasCancelled())
            {
                Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show();
                return;
            }else
            {
                if (result.getAccessToken() != null)
                    Toast.makeText(this,"Success ! %s"+result.getAccessToken().getAccountId(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this,"Success ! %s"+result.getAuthorizationCode().substring(0,10),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,UserResetPage.class));
            }
        }
    }
}
