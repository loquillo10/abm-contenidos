package com.tecnisenterpy.abmcontenidos.models;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "areas")
public class Areas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_area", length = 150, unique = true)
    private String nombreArea;
    @Column(name = "descripcion_area", length = 300)
    private String descripcionArea;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @OneToOne(mappedBy = "areas")
    private Internos internos;

    public Areas() {
    }

    public Areas(Integer id, String nombreArea, String descripcionArea) {
        this.id = id;
        this.nombreArea = nombreArea;
        this.descripcionArea = descripcionArea;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getDescripcionArea() {
        return descripcionArea;
    }

    public void setDescripcionArea(String descripcionArea) {
        this.descripcionArea = descripcionArea;
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

    public Internos getInternos() {
        return internos;
    }

    public void setInternos(Internos internos) {
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

    public void copyDataFromArea(Areas source){
        this.nombreArea = source.getNombreArea();
        this.descripcionArea = source.getDescripcionArea();
    }

    @Override
    public String toString() {
        return "Areas{" +
                "id=" + id +
                ", nombreArea='" + nombreArea + '\'' +
                ", descripcionArea='" + descripcionArea + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Areas areas = (Areas) o;
        return id.equals(areas.id) && nombreArea.equals(areas.nombreArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreArea);
    }
}
