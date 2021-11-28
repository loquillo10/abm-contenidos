package com.tecnisenterpy.abmcontenidos.models;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "secciones")
public class Secciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre", length = 150, unique = true)
    private String nombreSeccion;
    @Column(name = "descripcion", length = 500)
    private String descripcionSeccion;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificion")
    private LocalDateTime fechaModificacion;


    @ManyToMany(mappedBy = "secciones")
    private List<Internos> internos;

    public Secciones() {
    }

    public Secciones(Integer id, String nombreSeccion, String descripcionSeccion, LocalDateTime fechaAlta, LocalDateTime fechaModificacion) {
        this.id = id;
        this.nombreSeccion = nombreSeccion;
        this.descripcionSeccion = descripcionSeccion;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getDescripcionSeccion() {
        return descripcionSeccion;
    }

    public void setDescripcionSeccion(String descripcionSeccion) {
        this.descripcionSeccion = descripcionSeccion;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Internos> getInternos() {
        return internos;
    }

    public void setInternos(List<Internos> internos) {
        this.internos = internos;
    }

    @PrePersist
    public void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }
    @PreUpdate
    public void antesDeUpdate(){
        this.fechaModificacion = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Secciones{" +
                "id=" + id +
                ", nombreSeccion='" + nombreSeccion + '\'' +
                ", descripcionSeccion='" + descripcionSeccion + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secciones secciones = (Secciones) o;
        return id.equals(secciones.id) && nombreSeccion.equals(secciones.nombreSeccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreSeccion);
    }
}
