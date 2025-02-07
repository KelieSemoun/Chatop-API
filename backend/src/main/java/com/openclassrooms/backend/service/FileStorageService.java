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
	
	private final Path fileDirectory = Paths.get("src/images/upload/"); 

	public String savePicture(MultipartFile picture) throws IOException {
		 String fileName = UUID.randomUUID().toString() + "-" + picture.getOriginalFilename();
		    try {
		      Path targetLocation = fileDirectory.resolve(fileName);
		      Files.copy(picture.getInputStream(), targetLocation);
		      return fileName;
		    } catch (IOException e) {
		      throw new RuntimeException("Could not store file " + fileName, e);
		    }
	}

}
