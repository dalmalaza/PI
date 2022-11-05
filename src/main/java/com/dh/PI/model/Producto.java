package com.dh.PI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.engine.internal.Cascade;
import org.hibernate.property.access.spi.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="productos")
public class Producto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Long id;
    @Column
    private String titulo;
    @Column
    private String descripcion;

    // Probamos hacerla Bidireccional en su relacion con Categoria:
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    // Probamos hacerla Bidireccional en su relacion con Ciudad:
    @ManyToOne
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id")
    private Ciudad ciudad;

    @Column
    private Double precio;

    // Probamos hacerla Unidireccional en su relacion con Imagen:
    /* @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private Set<Imagen> imagenList = new HashSet<>();
    */

    // bidireccional imagen-producto <------ creo que deberia ser asi
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<Imagen> imagenList = new HashSet<>();


    // Probamos hacerla Unidireccional en su relacion con Caracteristica:
    @ManyToMany
    @JoinTable(name = "caracteristica_x_producto",
            joinColumns = @JoinColumn(name = "producto_id" ),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id")
    )
    private Set<Caracteristica> caracteristicaList = new HashSet<>();

    // Probamos hacerla Unidireccional en su relacion con Politica:
    @ManyToMany
    @JoinTable(name = "politica_x_producto",
            joinColumns = @JoinColumn(name = "producto_id" ),
            inverseJoinColumns = @JoinColumn(name = "politica_id")
    )
    private Set<Politica> politicaList = new HashSet<>();

    // Constructores

    public Producto(String titulo, String descripcion, Categoria categoria, Ciudad ciudad, Double precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.precio = precio;
    }
    public Producto() {
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
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Ciudad getCiudad() {
        return ciudad;
    }
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Set<Imagen> getImagenList() {
        return imagenList;
    }
    public void setImagenList(Set<Imagen> imagenList) {
        this.imagenList = imagenList;
    }
    public Set<Caracteristica> getCaracteristicaList() {
        return caracteristicaList;
    }
    public void setCaracteristicaList(Set<Caracteristica> caracteristicaList) {
        this.caracteristicaList = caracteristicaList;
    }
    public Set<Politica> getPoliticaList() {
        return politicaList;
    }
    public void setPoliticaList(Set<Politica> politicaList) {
        this.politicaList = politicaList;
    }

    // controlamos que todos los datos esten completos:
    public static boolean isProductoCompleto(Producto producto){
        if(producto!=null && producto.getTitulo()!=null && producto.getDescripcion()!=null && producto.getCategoria()!=null && producto.getCiudad()!=null && producto.getPrecio()!=null){
            return true;
        }else{
            return false;
        }
    }

}
