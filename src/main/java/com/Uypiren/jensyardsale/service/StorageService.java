package com.Uypiren.jensyardsale.service;


import com.Uypiren.jensyardsale.model.images.ImageData;
import com.Uypiren.jensyardsale.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@Service
public class StorageService {
    //private final String path = "/jensyardsale-frontend/images/";

    @Autowired
    private ImageRepository imageRepository;
    private Path docStorageLocation;

    public StorageService() throws IOException {
        //this.docStorageLocation = Paths.get(documentStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();


        // this.docStorageLocation = Paths.get("jensyardsale-frontend\\public\\doc-uploads").toAbsolutePath().normalize();

        StringBuilder path = new StringBuilder();
//        path.append(docStorageLocation);
//        path.append("\\jensyardsale-frontend\\images");
//        if (!Files.exists(docStorageLocation)) {
//            System.out.println("THE PATH WE ARE HOPEING TO CREATE IS>>>>>>>>" + this.docStorageLocation);
//            Files.createDirectories(this.docStorageLocation);
//
//        }
    }


    public String uploadImageToFileSystem(MultipartFile file, String id, String isMain) throws IOException {

        docStorageLocation = Paths.get("jensyardsale-frontend\\public\\doc-uploads\\" + id).toAbsolutePath().normalize();
        if (!Files.exists(docStorageLocation)) {
            System.out.println("THE PATH WE ARE HOPEING TO CREATE IS>>>>>>>>" + this.docStorageLocation);
            Files.createDirectories(this.docStorageLocation);

        }
        System.out.println(file.getName() + " is name");
        System.out.println(file.getContentType() + " is content type");
        System.out.println(file.getOriginalFilename() + " is ORIGINAL Filename");

        System.out.println(docStorageLocation);
        ImageData image = new ImageData();
        image.setName(file.getOriginalFilename());
        image.setFilePath(docStorageLocation.toString());
        image.setType(file.getContentType());
        //store itemID to the image database
        long itemId = Long.parseLong(id);
        image.setItemId(itemId);

        boolean isMainImage = isMain.equalsIgnoreCase("true") ? true : false;
        image.setPrimary(isMainImage);
        file.transferTo(new File(docStorageLocation.toString() + "\\" + file.getOriginalFilename()));

        String filePath = file.getOriginalFilename();


        if (image != null) {
            imageRepository.save(image);
            return "File uploaded successfully: " + filePath;
        }
        return null;
    }


    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {

        Optional<ImageData> fileData = imageRepository.findByName(fileName);
        //String filePath = fileData.get().getFilePath();
        StringBuilder filePath = new StringBuilder(fileData.get().getFilePath());
        filePath.append("\\");
        filePath.append(fileData.get().getName());
        // String filePath2 = "C:\\Users\\puyth\\JavaWebSeriviceProjects\\jensyardsale\\jensyardsale-frontend\\doc-uploads\\bulma-correct.png";
        byte[] images = Files.readAllBytes(new File(filePath.toString()).toPath());
        return images;

    }


}