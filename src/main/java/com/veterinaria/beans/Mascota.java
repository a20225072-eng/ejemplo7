package com.veterinaria.beans;
public class Mascota {
    private int idmascota;
    private String nombre;
    private int edad;
    private double peso;
    private Especie especie;
    private Veterinario veterinario;
    private Dueno dueno;
    public int getIdmascota() { return idmascota; }
    public void setIdmascota(int idmascota) { this.idmascota = idmascota; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    public Especie getEspecie() { return especie; }
    public void setEspecie(Especie especie) { this.especie = especie; }
    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }
    public Dueno getDueno() { return dueno; }
    public void setDueno(Dueno dueno) { this.dueno = dueno; }
}