package epam.com.JdbcAdvanced.controller;

import epam.com.JdbcAdvanced.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/get/{id}")
    public void getFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        fileService.getFile(id, response);
    }

    @PostMapping("/storeFile")
    public String storeFile(@RequestParam(name = "file") MultipartFile file) {
        return fileService.storeFile(file);
    }
}
