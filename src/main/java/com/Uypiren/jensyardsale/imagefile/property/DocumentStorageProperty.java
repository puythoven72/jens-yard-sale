package com.Uypiren.jensyardsale.imagefile.property;

import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "document")
@Component
public class DocumentStorageProperty {

    private String uploadDirectory;


    public String getUploadDirectory() {
        return uploadDirectory;
    }

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }
}
