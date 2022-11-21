package com.example.demo.controller.storage;

import com.example.demo.factory.ResponseFactory;
import com.example.demo.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.demo.constants.StorageBucket.IMAGE;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final MinioService minioService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam(value = "file") MultipartFile file) {
        return ResponseFactory.success(minioService.putObject(IMAGE.value(), file));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getImage(@PathVariable String name) {
        byte[] data = minioService.getObject(IMAGE.value(), name);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .contentType(MediaType.IMAGE_PNG)
                .body(data);
    }

    @GetMapping(value = "/download/{name}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String name) {
        byte[] data = minioService.getObject(IMAGE.value(), name);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + name + "\"")
                .body(resource);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteImage(@PathVariable String name) {
        minioService.deleteObject(IMAGE.value(), name);
        return ResponseFactory.success();
    }

}
