package com.Uypiren.jensyardsale.controller;

import com.Uypiren.jensyardsale.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("api/items/image-admin")
@CrossOrigin(origins = "http://localhost:3000")
public class FileImageController {
    @Autowired
    StorageService storageService;

    {
        try {
            storageService = new StorageService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping()
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }






    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        System.out.println(fileName + " IS WHAT WE ARE GETTING");
        byte[] image = storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.valueOf("image/png"))
                .body(image);

    }
}
