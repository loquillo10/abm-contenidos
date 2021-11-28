package com.tecnisenterpy.abmcontenidos.controllers;

import com.tecnisenterpy.abmcontenidos.models.Areas;
import com.tecnisenterpy.abmcontenidos.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internos")
public class AreasController {

    @Autowired
    private AreaRepository service;

    @GetMapping("/areas")
    public List<Areas> listarAreas(){
        return (List<Areas>) service.findAll();
    }

    @GetMapping("/areas/{id}")
    public ResponseEntity<Areas> buscarArea(@PathVariable int id){
        //creamos un optional para buscar el area
        Optional<Areas> getArea = service.findById(id);
        if (getArea.isPresent()){
            return ResponseEntity.ok(getArea.get());
        }
       else{
            return ResponseEntity.notFound().build();
        }
    }

    //Creamos para el Guardar Areas
    @PostMapping("/areas")
    public Areas guardarArea(@RequestBody Areas a){
        return service.save(a);
    }


    //creamos el point para actualizar
    @PutMapping("/areas/{id}")
    public ResponseEntity<Areas> areasUpdate(@PathVariable int id, @RequestBody Areas updateArea){
        //buscamos el Area
        Optional<Areas> findArea = service.findById(id);
        //si lo encontramos lo traemos para utilizarlo
        if (findArea.isPresent()){
            Areas actualizarArea = findArea.get();
            //copiamos los datos
            actualizarArea.copyDataFromArea(updateArea);
            //Guardamos los datos copiados
            Areas guardarArea = service.save(actualizarArea);
            return ResponseEntity.ok(guardarArea);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Point para Eliminar

    public ResponseEntity<Boolean> eliminarArea(@PathVariable int id){
        //traemos el area a eliminar
        Optional<Areas> buscarArea = service.findById(id);
        //si lo encontramos procedemos a eliminar
        if (buscarArea.isPresent()){
            service.delete(buscarArea.get());
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
