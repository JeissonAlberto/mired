package com.aldhix.loginapi;

import static com.aldhix.loginapi.R.id.etTelefono;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword, etCedula, etTelefono, etDireccion, etConfirmation;
    TextView etTerms;
    Button btnRegister, btnCancel;
    String name, email, password, cedula, direccion, telefono, confirmation;

    @SuppressLint({"MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(getApplicationContext()));

//        getSupportActionBar().setTitle("REGISTRARSE");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etCedula = findViewById(R.id.etCedula);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        etDireccion = findViewById(R.id.etDireccion);
        etTerms = findViewById(R.id.etTerms);

        etPassword = findViewById(R.id.etPassword);
        etConfirmation = findViewById(R.id.etConfirmation);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.btnCancel);
        CheckBox checkBoxTermsConditions = findViewById(R.id.checkBoxTermsConditions);
// Dentro del método donde ocurre el registro o cuando el usuario toque el botón "Registrarse"
// Puedes inflar el diseño del diálogo y mostrarlo en un AlertDialog.
        etTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_tems, null);
                builder.setView(dialogView);
// Crea y muestra el diálogo
                AlertDialog dialog = builder.create();
                dialog.show();
// Obtiene el botón de aceptar y configura su acción
                Button btnAccept = dialogView.findViewById(R.id.btn_accept);
                btnAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkBoxTermsConditions.setChecked(true);
                        dialog.dismiss();
                    }
                });


            }
        });



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxTermsConditions.isChecked()) {
                    checkRegister();
                }else {
                    alertFail("Debes aceptar los terminos y conidicones");
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCancel();
            }
        });

    }

    private void checkCancel() {
        finish();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void checkRegister() {
        name = etName.getText().toString();
        cedula = etCedula.getText().toString();
        email = etEmail.getText().toString();
        telefono = etTelefono.getText().toString();
        direccion = etDireccion.getText().toString();
        password = etPassword.getText().toString();
        confirmation = etConfirmation.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {

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
            params.put("telefono", telefono);
            params.put("direccion", direccion);
            params.put("cedula", cedula);
            params.put("password", password);
            params.put("password_confirmation", confirmation);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data = params.toString();
        String url = getString(R.string.api_server) + "/createUser";

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(RegisterActivity.this, url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 201 || code == 200) {
                            alertSuccess("Registrado exitosamente.");
                        } else if (code >200) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail(msg);

                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error", +Toast.LENGTH_SHORT).show();
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
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    finish();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

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
