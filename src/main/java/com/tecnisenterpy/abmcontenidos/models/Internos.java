package com.tecnisenterpy.abmcontenidos.models;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Internos")
public class Internos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @Column(name = "nro_interno")
    private Integer nroInterno;
    @Column(name = "correo", unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "areas", foreignKey = @ForeignKey(name = "FK_AREA_INTERNO"))
    private Areas areas;

    @JoinTable(
            name = "seccion_interno",
            joinColumns = @JoinColumn(name = "FK_SECCION_INTERNO", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_INTERNO_SECCION", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Secciones> secciones;

    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;


    public Internos() {
    }

    public Internos(Integer id, String nombre, Integer nroInterno, String email, Areas areas, List<Secciones> secciones) {
        this.id = id;
        this.nombre = nombre;
        this.nroInterno = nroInterno;
        this.email = email;
        this.areas = areas;
        this.secciones = secciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNroInterno() {
        return nroInterno;
    }

    public void setNroInterno(Integer nroInterno) {
        this.nroInterno = nroInterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Areas getAreas() {
        return areas;
    }

    public void setAreas(Areas areas) {
        this.areas = areas;
    }

    public List<Secciones> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<Secciones> secciones) {
        this.secciones = secciones;
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

    @PrePersist
    public void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }

    @PreUpdate
    public void antesDeUpdate(){
        this.fechaModificacion = LocalDateTime.now();
    }

    public void copyDataFromInterno(Internos source)
    {
        this.nombre = source.getNombre();
        this.nroInterno = source.getNroInterno();
        this.email = source.getEmail();
        this.areas = source.getAreas();
        this.secciones = source.getSecciones();
    }

    @Override
    public String toString() {
        return "Internos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nroInterno=" + nroInterno +
                ", email='" + email + '\'' +
                ", areas=" + areas +
                ", secciones=" + secciones +
                ", fechaAlta=" + fechaAlta +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Internos internos = (Internos) o;
        return id.equals(internos.id) && email.equals(internos.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
