package com.aldhix.loginapi;

import static com.aldhix.loginapi.R.id.etEmail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    String email, password;
    LocalStorage localStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Objects.requireNonNull(getSupportActionBar()).setTitle("INGRESAR");
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(getApplicationContext()));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        localStorage = new LocalStorage(LoginActivity.this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        Log.e("general", "pase por aca");
    btnLogin.setOnClickListener(view -> checkLogin());
    btnRegister.setOnClickListener(v -> {
    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
    startActivity(intent);
});


  }
    private void checkLogin(){
        email = etEmail.getText().toString();
        Log.e("email", email.toString());
        password = etPassword.getText().toString();
        Log.e("email", email.toString());
        if (email.isEmpty() || password.isEmpty()) {
            alertFail("Usuario y contraceña requeridos.");
        }
        else{
            sendLogin();
        }
    }

    private void sendLogin() {
        JSONObject params = new JSONObject();
        try {
            params.put("email",email);
            params.put("password",password);
        }catch (JSONException e){
            e.printStackTrace();
        }
        String data =  params.toString();
        String url = getString(R.string.api_server)+"/login";

        new Thread(() -> {
            Http http = new Http(LoginActivity.this, url);
            http.setMethod("post");
            http.setData(data);
            http.send();
            Log.e("voy a hacer la peticion", url);
            runOnUiThread(() -> {
                Integer code = http.getStatusCode();
                Log.e("code", code.toString());
                if (code==200) {
                  try {
                      JSONObject response = new JSONObject(http.getResponse());
                      String token = response.getString("token");
                      localStorage.setToken(token);
                      localStorage.setEmail(email);
                      finish();Log.e("token", token.toString());
                      Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                      startActivity(intent);
                  }catch (JSONException e){
                      e.printStackTrace();
                  }
                }
                else if (code == 422){
                    try {
                      JSONObject response = new JSONObject(http.getResponse());
                      String msg = response.getString("message");
                      alertFail(msg);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                else if (code == 401){
                    try {
                        JSONObject response = new JSONObject(http.getResponse());
                        String msg = response.getString("message");
                        alertFail(msg);


                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error"+code, Toast.LENGTH_SHORT).show();
                }
            });
        }).start();


    }

    private void alertFail(String s) {

        new  AlertDialog.Builder(this)
                .setTitle("Failed")
                .setIcon(R.drawable.ic_warning)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }
}