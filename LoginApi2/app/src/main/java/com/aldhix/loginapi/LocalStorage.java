package com.aldhix.loginapi;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    String token;
    String email;
    String name;
    String cedula;
    String direccion;
    String telefono;
    String ssid;
    String serviceId;
    String userId;

    public LocalStorage(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("STORAGE_LOGIN_API", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public String getToken() {
        token = sharedPreferences.getString("TOKEN", "");
        return token;

    }

    public void setToken(String token) {
        editor.putString("TOKEN", token);
        editor.commit();
        this.token = token;
    }

    public void setEmail(String email) {
        editor.putString("EMAIL", email);
        editor.commit();
        this.email = email;
    }
    public String getEmail() {
        email = sharedPreferences.getString("EMAIL", "");
        return email;

    }

    public String getName() {
        name = sharedPreferences.getString("NAME", "");
        return name;

    }

    public void setName(String name) {
        editor.putString("NAME", name);
        editor.commit();
        this.name = name;
    }

    public void setCedula(String cedula) {
        editor.putString("CEDULA", cedula);
        editor.commit();
        this.cedula = cedula;
    }

    public String getCedula() {
        cedula = sharedPreferences.getString("CEDULA", "");
        return cedula;

    }

    public void setDireccion(String direccion) {
        editor.putString("DIRECCION", direccion);
        editor.commit();
        this.direccion = direccion;
    }
    public String getDireccion() {
        direccion = sharedPreferences.getString("DIRECCION", "");
        return direccion;

    }

    public void setTelefono(String telefono) {
        editor.putString("TELEFONO", telefono);
        editor.commit();
        this.telefono = telefono;
    }
    public String getTelefono() {
        telefono = sharedPreferences.getString("TELEFONO", "");
        return telefono;

    }

    public void setSSID(String ssid) {
        editor.putString("SSID", ssid);
        editor.commit();
        this.ssid = ssid;
    }

    public String getSSID()  {
        ssid = sharedPreferences.getString("SSID", "");
        return ssid;
    }

    public void setServiceId(String id) {
        editor.putString("SERVICE_ID", id);
        editor.commit();
        this.serviceId = id;
    }
    public String getServiceId()  {
        serviceId = sharedPreferences.getString("SERVICE_ID", "");
        return serviceId;
    }

    public void setUserId(String id) {
        editor.putString("USER_ID", id);
        editor.commit();
        this.userId = id;
    }
    public String getUserId()  {
        userId = sharedPreferences.getString("USER_ID", "");
        return userId;
    }
}
