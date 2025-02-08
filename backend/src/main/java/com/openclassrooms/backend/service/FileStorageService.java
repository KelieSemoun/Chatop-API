package com.openclassrooms.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	
	private final Path fileDirectory = Paths.get(System.getProperty("user.dir"), "images");
	
	public String savePicture(MultipartFile picture) throws IOException {
		
		if(picture == null) {
			throw new NullPointerException("Picture cannot be null");
		}
		
		if (!Files.exists(fileDirectory)) {
            Files.createDirectories(fileDirectory);
        }
		
		String fileName = UUID.randomUUID().toString() + "-" + picture.getOriginalFilename();
		try {
			Path targetLocation = fileDirectory.resolve(fileName);
		    Files.copy(picture.getInputStream(), targetLocation);
		    return "http://localhost:8080/images/" + fileName;
		} catch (IOException e) {
		    throw new RuntimeException("Could not store file " + fileName, e);
		}
	}

}
