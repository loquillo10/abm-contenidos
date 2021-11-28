package com.tecnisenterpy.abmcontenidos.controllers;

import com.tecnisenterpy.abmcontenidos.models.Secciones;
import com.tecnisenterpy.abmcontenidos.repositories.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internos")
public class SeccionesController {

    @Autowired
    private SeccionRepository service;

    @GetMapping("/secciones")
    public List<Secciones> listaSecciones(){
        return (List<Secciones>) service.findAll();
    }

    @GetMapping("/secciones/{id}")
    public ResponseEntity<Secciones> getSecciones(@PathVariable Integer id){
        //buscamos la seccion y la traemos
        Optional<Secciones> finSeccion = service.findById(id);
        //ahora lo traemos
        if(finSeccion.isPresent()){
            return ResponseEntity.ok(finSeccion.get());
        }else
        {
            //si hay error lo trabajamos
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/secciones")
    public Secciones guardarSeccion(@RequestBody Secciones s){
        return service.save(s);
    }


    @PutMapping("/secciones/{id}")
    public ResponseEntity<Secciones> actualizarSeccion(@PathVariable Integer id, @RequestBody Secciones seccionUpdate){
        //traemos la seccion con un optional
        Optional<Secciones> seccionesUpdate = service.findById(id);
        //extreamos la informacion
        if (seccionesUpdate.isPresent()){
            Secciones seccionData = seccionesUpdate.get();
            //copiamos los datos
            seccionData.copyDataFromSeccion(seccionUpdate);
            return ResponseEntity.ok(seccionData);
        }else {
            //si tenemos un error lo trabajamos
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Boolean> eliminarSeccion(@PathVariable Integer id){
        //buscamos la seccion para eliminar
        Optional<Secciones> findSeccion = service.findById(id);
        if (findSeccion.isPresent()){
            service.delete(findSeccion.get());
            return ResponseEntity.ok(true);
        }else{
            //si tenemos un error lo trabajamos
            return ResponseEntity.notFound().build();
        }
    }
}
