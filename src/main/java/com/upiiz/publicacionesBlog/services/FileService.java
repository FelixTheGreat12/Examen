package com.upiiz.publicacionesBlog.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upiiz.publicacionesBlog.models.PublicacionesBlog;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private static final String FILE_PATH = "src/main/resources/publicacionesBlog.json";

    public void guardarPublicacion(PublicacionesBlog cliente){
        List<PublicacionesBlog> publicacionesBlogs = new ArrayList<>();

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(FILE_PATH);
            if (!file.exists()){
                PublicacionesBlog[] existingClientes =objectMapper.readValue(file,PublicacionesBlog[].class);
                for (PublicacionesBlog c : existingClientes){
                    publicacionesBlogs.add(c);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        publicacionesBlogs.add(cliente);

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(FILE_PATH), publicacionesBlogs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
