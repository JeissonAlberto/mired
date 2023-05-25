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

public class ServicesActivityModem extends AppCompatActivity {
    TextView tvVelocidad, tvPrecio, tvTecnologia, tvSSID;
    Button btnUpdate;

    private LocalStorage localStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_activitymodem);
        localStorage = new LocalStorage(ServicesActivityModem.this);
        tvVelocidad = findViewById(R.id.tvVelocidad);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvTecnologia = findViewById(R.id.tvTecnologia);
        tvSSID = findViewById(R.id.tvSSID);
        btnUpdate = findViewById(R.id.btnUpdate);

        getUser();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizar();
            }
        });

    }

    private void actualizar() {
        Intent intent = new Intent(ServicesActivityModem.this, UpdatePasswordActivity.class);
        startActivity(intent);
        finish();
    }

    private void getUser() {
        String url = getString(R.string.api_server)+"/servicesByUserEmail/"+localStorage.getEmail();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(ServicesActivityModem.this, url);
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
                                String velocidad = response.getString("velocidad");
                                String precio = response.getString("precio");
                                String tecnologia = response.getString("tipo_de_tecnologia");
                                String ssid = response.getString("ssid");
                                localStorage.setSSID(ssid);
                                localStorage.setServiceId(response.getString("id"));
                                tvTecnologia.setText(tecnologia);
                                tvPrecio.setText(precio);
                                tvSSID.setText(ssid);
                                tvVelocidad.setText(velocidad);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        else {
                            Toast.makeText(ServicesActivityModem.this, "Error"+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }
}