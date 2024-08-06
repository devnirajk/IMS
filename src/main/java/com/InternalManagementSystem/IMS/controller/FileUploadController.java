package com.InternalManagementSystem.IMS.controller;

import com.InternalManagementSystem.IMS.util.ResponseHandler;
import com.InternalManagementSystem.IMS.service.serviceImplementation.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/upload-file")
public class FileUploadController {

    @Autowired
    UploadService uploadService;


    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
        }
        try {
            String url = uploadService.uploadFile(file);
            return ResponseHandler.generateResponse("File uploaded successfully!!!", HttpStatus.OK, url);
        } catch (IOException e) {
            return ResponseHandler.generateResponse("Could not upload the file. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}