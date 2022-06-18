package com.switch_and_trade.switch_and_trade_artifact.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FotoServicio {
    private static final String DIRECTORY = "src/main/resources/static/uploads";

    public String copy(MultipartFile foto) {
        try {
            String photoName = foto.getOriginalFilename();
            Path photoPath = Paths.get(DIRECTORY, photoName).toAbsolutePath();
            Files.copy(foto.getInputStream(), photoPath, StandardCopyOption.REPLACE_EXISTING);
            return photoName;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error saving image");
        }
    }
}
