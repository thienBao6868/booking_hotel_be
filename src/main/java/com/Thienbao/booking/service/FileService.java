package com.Thienbao.booking.service;

import com.Thienbao.booking.exception.SaveFileException;
import com.Thienbao.booking.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileService implements FileServiceImp {

    @Value("${path.save-file}")
    private  String pathSaveFile;

    @Override
    public boolean saveFile(MultipartFile file) {
        try{
            Path root = Paths.get(pathSaveFile);
            if (!Files.exists(root)){
                Files.createDirectory(root);
            }
            Files.copy(file.getInputStream(),root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            return true;
        }catch(Exception ex){
            throw new SaveFileException("Error save file" + ex.getMessage());
        }
    }
}
