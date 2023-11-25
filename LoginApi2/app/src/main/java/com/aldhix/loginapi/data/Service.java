package com.aldhix.loginapi.data;

public class Service {

    private String id;
    private String velocidad;
    private String tipo_de_tecnologia;
    private String precio;
    private String ssid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getTipo_de_tecnologia() {
        return tipo_de_tecnologia;
    }

    public void setTipo_de_tecnologia(String tipo_de_tecnologia) {
        this.tipo_de_tecnologia = tipo_de_tecnologia;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }



    public Service(String id, String velocidad, String tipo_de_tecnologia, String precio, String ssid) {
        this.id = id;
        this.velocidad = velocidad;
        this.tipo_de_tecnologia = tipo_de_tecnologia;
        this.precio = precio;
        this.ssid = ssid;
    }
}


