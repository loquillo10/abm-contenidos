package com.tecnisenterpy.abmcontenidos.controllers;

import com.tecnisenterpy.abmcontenidos.models.Internos;
import com.tecnisenterpy.abmcontenidos.repositories.InternoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/internos")
public class InternosController {

    @Autowired
    private InternoRepository service;

    @GetMapping("/listainternos")
    public List<Internos> listaInternos(){
        return (List<Internos>) service.findAll();
    }

    @GetMapping("/listainternos/{id}")
    public ResponseEntity<Internos> getInternoById(@PathVariable int id) throws ClassNotFoundException {
        //es un optional
        Optional<Internos> I = service.findById(id);
        //si esta presente se devuelve
        if(I.isPresent()){
            return ResponseEntity.ok(I.get());
        }//sino , lanzamos una excepcion
        else{
            throw new ClassNotFoundException("Not found Internos by Id " + id);
        }
    }

    @PostMapping("/listainternos")
    public Internos agregarInternos(@RequestBody Internos I){
        return service.save(I);
    }

    @PutMapping("/listainternos/{id}")
    public ResponseEntity<Internos> actualizarInternos(@PathVariable int id, @RequestBody Internos internoUpdate) throws ClassNotFoundException {
        //buscar un interno, para actualizar y lo traemos
        Optional<Internos> GetInterno = service.findById(id);
        //si lo encontramos, lo traemos
        if (GetInterno.isPresent()){
            //Interno encontrado para actualizar
            Internos internoToUpdate = GetInterno.get();
            //obtenemos los nuevos datos..
            internoToUpdate.copyDataFromInterno(internoUpdate);
            //Guardar en BD
            Internos internosSave = service.save(internoToUpdate);
            return  ResponseEntity.ok(internosSave);
        }else
        {
            throw new ClassNotFoundException("Not Found Internos By Id " + id);
        }
    }



}
