package com.upiiz.publicacionesBlog.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upiiz.publicacionesBlog.models.PublicacionesBlog;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PublicacionesBlogRepository {
    private static final String FILE_PATH = "src/main/resources/publicacionesBlog.json";
    private List<PublicacionesBlog> publicacionesBlogs = new ArrayList<>();
    private AtomicLong id = new AtomicLong();

    public PublicacionesBlogRepository() {
        this.leerPublicacionesDeArchivo();
    }

    public PublicacionesBlog guardar(PublicacionesBlog publicacionesBlog) {
        publicacionesBlog.setId(id.incrementAndGet());
        this.publicacionesBlogs.add(publicacionesBlog);
        this.guardarPublicacionesEnArchivo();
        return publicacionesBlog;
    }

    public List<PublicacionesBlog> obtenerTodas() {
        return this.publicacionesBlogs;
    }

    public PublicacionesBlog obtenerPorId(Long id) {
        return this.publicacionesBlogs.stream().filter(publicacion -> publicacion.getId().equals(id)).findFirst().orElse(null);
    }

    public void eliminar(Long id) {
        this.publicacionesBlogs.removeIf(publicacion -> publicacion.getId().equals(id));
        this.guardarPublicacionesEnArchivo();
    }

    public PublicacionesBlog actualizar(PublicacionesBlog publicacionesBlog) {
        this.eliminar(publicacionesBlog.getId());
        this.publicacionesBlogs.add(publicacionesBlog);
        this.guardarPublicacionesEnArchivo();
        return publicacionesBlog;
    }


    private void leerPublicacionesDeArchivo() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            File archivo = new File(FILE_PATH);
            if (archivo.exists()) {
                PublicacionesBlog[] publicacionesArray = objectMapper.readValue(archivo,PublicacionesBlog[].class);
                this.publicacionesBlogs = new ArrayList<>(Arrays.asList(publicacionesArray));

                if (!this.publicacionesBlogs.isEmpty()) {
                    Long maxId = this.publicacionesBlogs.stream().mapToLong(PublicacionesBlog::getId).max().orElse(0);
                    this.id.set(maxId);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
    }

    private void guardarPublicacionesEnArchivo() {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), this.publicacionesBlogs);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo JSON", e);
        }
    }
}
