package com.upiiz.publicacionesBlog.controllers;

import com.upiiz.publicacionesBlog.models.PublicacionesBlog;
import com.upiiz.publicacionesBlog.services.FileService;
import com.upiiz.publicacionesBlog.services.PublicacionesBlogsServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/publicaciones")
@RestController
public class PublicacionesBlogsController {

    private final PublicacionesBlogsServices publicacionesBlogsServices;

    public PublicacionesBlogsController(PublicacionesBlogsServices publicacionesBlogsServices, FileService fileService) {
        this.publicacionesBlogsServices = publicacionesBlogsServices;
    }

    @GetMapping
    public ResponseEntity<List<PublicacionesBlog>> getPublicaicones(){
        return ResponseEntity.ok(publicacionesBlogsServices.getAllPublicacionesBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionesBlog> getPublicacionPorId(@PathVariable Long id){
        return ResponseEntity.ok(publicacionesBlogsServices.getPublicacionesIDBlog(id));
    }

    @PostMapping
    public ResponseEntity<PublicacionesBlog> addPublicacion(@RequestBody PublicacionesBlog empleado,@PathVariable Long id){
        PublicacionesBlog nuevoCliente = publicacionesBlogsServices.createPublicacionesBlog(empleado);
        return ResponseEntity.ok(nuevoCliente);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionesBlog> actualizarPublicacion(@PathVariable Long id, @RequestBody PublicacionesBlog empleado){
        empleado.setId(id);
        return ResponseEntity.ok(publicacionesBlogsServices.updatePublicaciones(empleado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id){
        publicacionesBlogsServices.deletePublicacionesBlog(id);
        return ResponseEntity.noContent().build();
    }

}
