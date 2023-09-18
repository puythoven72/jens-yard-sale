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


//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
//    public Object upload(@RequestPart("logo") MultipartFile logo,
//                         @RequestPart("name") String name,
//                         @RequestPart("info") JsonObject info) throws IOException {


//

    @PostMapping()
    public ResponseEntity<?> uploadImageToFileSystem(@RequestPart("file") MultipartFile file,  @RequestPart("isMain") String isMain, @RequestPart("itemId") String itemId) throws IOException {
        System.out.println(isMain + " IsMAIN");
        System.out.println(itemId + " itemID");

        String uploadImage = storageService.uploadImageToFileSystem(file,itemId,isMain);
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
