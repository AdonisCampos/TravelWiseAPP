package com.example.travelwise;

public class Lugares {

    //1- comenzamos añadiendo los atributos a nuestros objetos
    String nombre;
    String descripcion;
    int imagen;
    // 2- generamos constructor, nos permite inicializar las variables
    public Lugares(String nombre, String descripcion, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
    //3- generamos getter

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagen() {
        return imagen;
    }
}
