package br.com.senac.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageUploadService {

    public String handleImageUpload(MultipartFile file){
        try{
            Path dirPath = Paths.get("uploads");
            if (!Files.exists(dirPath)){
                Files.createDirectories(dirPath);
            }

            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");

            Path uploadPath = dirPath.resolve(fileName);

            Files.write(uploadPath, file.getBytes());

            return "/uploads/" + fileName;


        } catch (Exception ex){
            throw new RuntimeException("Falha ao enviar imagem!");
        }
    }

}
