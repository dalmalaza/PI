package com.dh.PI.model;

import javax.persistence.*;

@Entity
@Table(name = "caracteristicas")
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id" )
    private Long id;
    @Column
    private String nombre;
    @Column
    private String icono;

    // Probamos hacerla Unidireccional en su relacion con Productos

    // Constructores
    public Caracteristica(String nombre, String icono) {
        this.nombre = nombre;
        this.icono = icono;
    }
    public Caracteristica() {
    }

    // Getters y Setters
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
    public String getIcono() {
        return icono;
    }
    public void setIcono(String icono) {
        this.icono = icono;
    }

    // controlamos que todos los datos esten completos:
    public static boolean isCaracteristicaCompleta(Caracteristica caracteristica){
        if(caracteristica!=null && caracteristica.getNombre()!=null && caracteristica.getIcono()!=null){
            return true;
        }else{
            return false;
        }
    }

}
