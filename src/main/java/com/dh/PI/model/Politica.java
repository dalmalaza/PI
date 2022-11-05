package com.dh.PI.model;

import javax.persistence.*;

@Entity
@Table(name = "politicas")
public class Politica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id" )
    private Long id;
    @Column
    private String titulo;
    @Column
    private String descripcion;

    // Probamos hacerla Unidireccional en su relacion con Productos

    // Constructores
    public Politica( String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }
    public Politica( ) {
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

    // controlamos que todos los datos esten completos:
    public static boolean isPoliticaCompleta(Politica politica){
        if(politica!=null && politica.getTitulo()!=null && politica.getDescripcion()!=null){
            return true;
        }else{
            return false;
        }
    }
}
