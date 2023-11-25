package com.aldhix.loginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityUpdateUser extends AppCompatActivity {
    EditText etName, etEmail, etPassword, etCedula,etTelefono, etDireccion, etConfirmation;
    Button btnRegister;
    String name, email, password, cedula,direccion,telefono,confirmation;
    private LocalStorage localStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        localStorage = new LocalStorage(ActivityUpdateUser.this);
        etName = findViewById(R.id.etName);
        etCedula = findViewById(R.id.etCedula);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);

        etPassword = findViewById(R.id.etPassword);
        etConfirmation = findViewById(R.id.etConfirmation);
        btnRegister = findViewById(R.id.btnRegister);

        etEmail.setText(localStorage.getEmail());
        etName.setText(localStorage.getName());
        etCedula.setText(localStorage.getCedula());
        etTelefono.setText(localStorage.getTelefono());
        etDireccion.setText(localStorage.getDireccion());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegister();
            }
        });
    }

    private void checkRegister() {
        name = etName.getText().toString();
        cedula = etCedula.getText().toString();
        email = etEmail.getText().toString();
        telefono = etTelefono.getText().toString();
        direccion = etDireccion.getText().toString();
        password = etPassword.getText().toString();
        confirmation = etConfirmation.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || direccion.isEmpty() ) {

            alertFail("Datos requeridos ");
        } else if (!password.equals(confirmation)) {
            alertFail("Contraceña no coincide.");
        } else {
            sendRegister();
        }
    }

    private void sendRegister() {
        JSONObject params = new JSONObject();
        try {
            params.put("name", name);
            params.put("email", email);
            params.put("password", password);
            params.put("password_confirmation", confirmation);

        }catch (JSONException e){
            e.printStackTrace();
        }
        String data = params.toString();
        String url = getString(R.string.api_server)+"/userUpdate/"+localStorage.getUserId();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(ActivityUpdateUser.this, url);
                http.setMethod("PUT");
                http.setData(data);
                http.setToken(true);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code ==201 || code ==200){
                            alertSuccess("Actualizado correctamente.");
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
                            Toast.makeText(ActivityUpdateUser.this, "Error",+ Toast.LENGTH_SHORT).show();
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
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        onBackPressed();

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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityUpdateUser.this, UserActivity.class);
        startActivity(intent);
        finish();
    }
}
