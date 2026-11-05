package com.travelworld.api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hoteles")
public class Hotel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ciudad;
    private String pais;
    @Column(length = 1000)
    private String imagen;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hotel_imagenes", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "imagen")
    private List<String> imagenes = new ArrayList<>();
    private Integer estrellas;
    private Double calificacion;
    private Integer opiniones;
    private BigDecimal precio;
    @Column(length = 3000)
    private String descripcion;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "hotel_servicios", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "servicio")
    private List<String> servicios = new ArrayList<>();
    private Double latitud;
    private Double longitud;

    public Hotel (){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public Integer getOpiniones() {
        return opiniones;
    }

    public void setOpiniones(Integer opiniones) {
        this.opiniones = opiniones;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getServicios() {
        return servicios;
    }

    public void setServicios(List<String> servicios) {
        this.servicios = servicios;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
