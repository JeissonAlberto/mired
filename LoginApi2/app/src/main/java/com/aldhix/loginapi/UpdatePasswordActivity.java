package com.aldhix.loginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdatePasswordActivity extends AppCompatActivity {
    EditText etPassword, etSSID;
    Button btnUpdate;

    String ssid, password;
    private LocalStorage localStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        localStorage = new LocalStorage(UpdatePasswordActivity.this);
        etPassword = findViewById(R.id.etPassword);
        etSSID = findViewById(R.id.etSSID);
        btnUpdate = findViewById(R.id.btnUpdate);

        getSSID();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRegister();
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UpdatePasswordActivity.this, ServicesActivityModem.class);
        startActivity(intent);
        finish();
    }
    private void getSSID() {
        etSSID.setText(localStorage.getSSID());
        }
    private void sendRegister() {
        ssid = etSSID.getText().toString();
        password = etPassword.getText().toString();
        JSONObject params = new JSONObject();
        try {
            params.put("ssid", ssid);
            params.put("password", password);
        }catch (JSONException e){
            e.printStackTrace();
        }
        String data = params.toString();
        String url = getString(R.string.api_server)+"/updatePassword/"+localStorage.getServiceId();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(UpdatePasswordActivity.this, url);
                http.setMethod("PUT");
                http.setData(data);
                http.setToken(true);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code ==201 || code ==200){
                            alertSuccess("Contraseña actualizada.");
                        }
                        else if (code == 422){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail("msg");

                            }catch (JSONException e){
                                e.printStackTrace();

                            }
                        }
                        else {
                            Toast.makeText(UpdatePasswordActivity.this, "Error",+ Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        }).start();

    }

    private void alertSuccess(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setIcon(R.drawable.ic_check)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent intent = new Intent(UpdatePasswordActivity.this, ServicesActivityModem.class);
                        startActivity(intent);
                        finish();

                    }
                })
                .show();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Failed")
                .setIcon(R.drawable.ic_warning)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).show();
    }
}
