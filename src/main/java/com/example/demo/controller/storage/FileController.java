package com.example.demo.controller.storage;

import com.example.demo.factory.ResponseFactory;
import com.example.demo.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.demo.constants.StorageBucket.FILE;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final MinioService minioService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return ResponseFactory.success(minioService.putObject(FILE.value(), file));
    }

    @GetMapping(value = "/download/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = minioService.getObject(FILE.value(), fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        minioService.deleteObject(FILE.value(), fileName);
        return ResponseFactory.success();
    }

}
