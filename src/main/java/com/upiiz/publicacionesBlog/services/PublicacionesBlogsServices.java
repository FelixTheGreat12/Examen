package com.upiiz.publicacionesBlog.services;

import com.upiiz.publicacionesBlog.models.PublicacionesBlog;
import com.upiiz.publicacionesBlog.repository.PublicacionesBlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PublicacionesBlogsServices {

    PublicacionesBlogRepository publicacionesBlogRepository;

    public PublicacionesBlogsServices(PublicacionesBlogRepository publicacionesBlogRepository) {
        this.publicacionesBlogRepository = publicacionesBlogRepository;
    }

    // Get
    public List<PublicacionesBlog> getAllPublicacionesBlogs() {
        return publicacionesBlogRepository.obtenerTodas();
    }

    // Categoria ID
    public PublicacionesBlog getPublicacionesIDBlog(Long id) {
        return publicacionesBlogRepository.obtenerPorId(id);
    }

    // Crear
    public PublicacionesBlog createPublicacionesBlog(PublicacionesBlog publicacionesBlog) {
        return publicacionesBlogRepository.guardar(publicacionesBlog);
    }

    // Actualizar
    public PublicacionesBlog updatePublicaciones(PublicacionesBlog publicacionesBlog) {
        return publicacionesBlogRepository.actualizar(publicacionesBlog);
    }

    // Eliminar
    public void deletePublicacionesBlog(Long id) {
        publicacionesBlogRepository.eliminar(id);
    }

}
