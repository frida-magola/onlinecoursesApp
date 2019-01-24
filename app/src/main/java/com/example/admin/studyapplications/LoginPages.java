package com.example.admin.studyapplications;

import android.app.Activity;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import prefs.UserInfo;
import prefs.UserSession;

public class LoginPages extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = LoginPages.class.getSimpleName();

    private EditText username;
    private EditText Password;
    private TextView forgotPassword;
    private Button Login, SignUp;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pages);

        username = (EditText) findViewById(R.id.txtUsername);
        Password = (EditText) findViewById(R.id.txtPassword);
        forgotPassword = (TextView) findViewById(R.id.btnforgotPass);
        SignUp = (Button) findViewById(R.id.btnsignUp);
        Login = (Button) findViewById(R.id.btnLogin);


        if(session.isUserLoggedin()){
            startActivity(new Intent(this, LoginPages.class));
            finish();
        }

        Login.setOnClickListener(this);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         validate(Username.getText().toString(), Password.getText().toString());

                Intent intent = new Intent(LoginPages.this, SignUpPage.class);

                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //         validate(Username.getText().toString(), Password.getText().toString());

                Intent intent = new Intent(LoginPages.this, ResetPasswordUser.class);

                startActivity(intent);
            }
        });



    }

    private void login(final String username, final String password){
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        progressDialog.setMessage("Logging in...");
        progressDialog.show();


        StringRequest strReq = new StringRequest(Request.Method.POST,
                Utils.LOGIN_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // Now store the user in SQLite
                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String surname = user.getString("surname");
                        String email = user.getString("emailaddress");
                        String contactnum = user.getString("contactnum");
                        String password = user.getString("password");
                        String role_type = user.getString("role_type");
                        String picture = user.getString("picture");


                        // Inserting row in users table
                        userInfo.setName(name);
                        userInfo.setSurname(surname);
                        userInfo.setEmailAddress(email);
                        userInfo.setContactnum(contactnum);
                        userInfo.setPassword(password);
                        userInfo.setRole_Type(role_type);
                        userInfo.setPicture(picture);
                        session.setLoggedin(true);

                        startActivity(new Intent(LoginPages.this, Navigation.class));
                        finish();
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        toast(errorMsg);
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    toast("Json error: " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                toast("Unknown Error occurred");
                progressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();
                params.put("email", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void toast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                String uName = username.getText().toString().trim();
                String pass  = Password.getText().toString().trim();

                login(uName, pass);
                break;
            case R.id.btnsignUp:
                startActivity(new Intent(this, SignUpPage.class));
                break;
        }
    }


}
