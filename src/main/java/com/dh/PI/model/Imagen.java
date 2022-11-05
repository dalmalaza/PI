package com.dh.PI.model;

import javax.persistence.*;

@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String titulo;
    @Column
    private String url;

    /* bidireccional imagen-producto <------ creo que deberia ser asi*/
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName= "id")
    private Producto producto;


    // Constructores
    public Imagen(String titulo, String url, Producto producto) {
        this.titulo = titulo;
        this.url = url;
        this.producto = producto;
    }
    public Imagen() {
    }

    // Getters y Setters
    public Long getId() {return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    // controlamos que todos los datos esten completos:
    public static boolean isImagenCompleta(Imagen imagen){
        if(imagen!=null && imagen.getTitulo()!=null && imagen.getUrl()!=null && imagen.getProducto()!=null){
            return true;
        }else{
            return false;
        }
    }
}
