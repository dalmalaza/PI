package com.dh.PI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id" )
    private Long id;
    @Column
    private String titulo;
    @Column
    private String descripcion;
    @Column
    private String urlImagen;

    // Probamos hacerla Bidireccional en su relacion con Producto:
    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private Set<Producto> productoList = new HashSet<>();

    // Constructores
    public Categoria(String titulo, String descripcion, String urlImagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }
    public Categoria() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getUrlImagen() {
        return urlImagen;
    }
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    // controlamos que todos los datos esten completos:
    public static boolean isCategoriaCompleta(Categoria categoria){
        if(categoria!=null && categoria.getTitulo()!=null && categoria.getDescripcion()!=null && categoria.getUrlImagen()!=null){
            return true;
        }else{
            return false;
        }
    }
}
