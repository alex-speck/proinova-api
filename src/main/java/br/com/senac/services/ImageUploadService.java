package br.com.senac.services;

import br.com.senac.exception.ImageUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ImageUploadService {

    public String handleImageUpload(MultipartFile file){
        try{
            validateFile(file);

            Path dirPath = Paths.get("uploads");
            if (!Files.exists(dirPath)){
                Files.createDirectories(dirPath);
            }

            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");

            Path uploadPath = dirPath.resolve(fileName);

            Files.write(uploadPath, file.getBytes());

            return "/uploads/" + fileName;


        } catch (IOException ex) {
            throw new ImageUploadException("Erro ao salvar arquivo." + ex.getLocalizedMessage());
        } catch (Exception ex) {
            throw new ImageUploadException("Falha ao enviar imagem." + ex.getLocalizedMessage());
        }
    }

    private void validateFile(MultipartFile file){
        if(file.isEmpty()){
            throw new ImageUploadException("A requisição deve conter um arquivo;");
        }
        List<String> acceptedExtensions = List.of("png","jpeg","jpg");

        if (!acceptedExtensions.contains(getExtension(file))){
            throw new ImageUploadException("Tipo de arquivo não é uma imagem suportada!");
        }

        if (file.getSize() > 5 * 1024 * 1024) {
            throw new ImageUploadException("Imagem maior que 5MB não é permitida.");
        }
    }

    private String getExtension(MultipartFile file){
        String originalName = file.getOriginalFilename();
        int dotIndex = originalName.lastIndexOf(".");

        if (dotIndex == -1 || dotIndex == originalName.length() - 1) {
            return "";
        }

        return originalName.substring(dotIndex + 1).toLowerCase();
    }

}
