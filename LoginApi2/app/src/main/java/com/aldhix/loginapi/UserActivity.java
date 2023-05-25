package com.aldhix.loginapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {
    TextView tvName, tvEmail,tvTelefono, tvDireccion, tvCedula ;
    Button btnLogout, btnUpdate, btnServicios;

    private LocalStorage localStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        localStorage = new LocalStorage(UserActivity.this);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvTelefono = findViewById(R.id.tvTelefono);
        tvCedula = findViewById(R.id.tvCedula);
        tvDireccion = findViewById(R.id.tvDireccion);
        btnLogout = findViewById(R.id.btnLogout);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnServicios = findViewById(R.id.btnServicios);

        getUser();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });

        btnServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                servicios();
            }
        });

    }
    private void getUser() {
      String url = getString(R.string.api_server)+"/user/"+localStorage.getEmail();
      new Thread(new Runnable() {
          @Override
          public void run() {
              Http http = new Http(UserActivity.this, url);
              Log.e("URL USER", url);
              http.setToken(true);
              http.setMethod("GET");
              http.send();

              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Integer code = http.getStatusCode();
                      Log.e("err code user", code.toString());
                      if (code ==200){
                          try {
                              JSONObject response = new JSONObject(http.getResponse());
                              String name = response.getString("name");
                              localStorage.setName(name);
                              String email = response.getString("email");
                              String cedula = response.getString("cedula");
                              localStorage.setCedula(cedula);
                              String direccion = response.getString("direccion");
                              localStorage.setDireccion(direccion);
                              String telefono = response.getString("telefono");
                              localStorage.setTelefono(telefono);
                              tvName.setText(name);
                              tvEmail.setText(email);
                              tvCedula.setText(cedula);
                              tvDireccion.setText(direccion);
                              tvTelefono.setText(telefono);
                              localStorage.setUserId(response.getString("id"));
                          }catch (JSONException e){
                              e.printStackTrace();
                          }
                      }
                      else {
                          Toast.makeText(UserActivity.this, "Error"+code, Toast.LENGTH_SHORT).show();
                      }
                  }
              });
          }
      }).start();
    }

    private void logout() {
       String url = getString(R.string.api_server)+"/logout";
       new  Thread(new Runnable() {
           @Override
           public void run() {
               Http http =new Http(UserActivity.this, url);
               http.setMethod("post");
               http.setToken(true);
               http.send();

               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Integer code = http.getStatusCode();
                       if (code == 200){
                           Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                           startActivity(intent);
                           finish();
                       }
                       else {
                           Toast.makeText(UserActivity.this, "Error"+code, Toast.LENGTH_SHORT).show();
                       }
                   }
               });

           }
       }).start();
    }

    private void actualizar() {
        Intent intent = new Intent(UserActivity.this, ActivityUpdateUser.class);
        startActivity(intent);
        finish();
    }

    private void servicios() {
        Log.e("token", "termine");
        Intent intent = new Intent(UserActivity.this, ServicesActivityModem.class);
        startActivity(intent);
        finish();
    }
}