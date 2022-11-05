package com.dh.PI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String provincia;
    @Column
    private String pais;

    // Probamos hacerla Bidireccional en su relacion con Producto:
    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private  Set<Producto> productoList = new HashSet<>();


    // Constructores
    public Ciudad(String nombre, String provincia, String pais) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.pais = pais;
    }
    public Ciudad() {
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
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    // controlamos que todos los datos esten completos:
    public static boolean isCiudadCompleta(Ciudad ciudad){
        if(ciudad!=null && ciudad.getNombre()!=null && ciudad.getProvincia()!=null && ciudad.getPais()!=null){
            return true;
        }else{
            return false;
        }
    }
}
