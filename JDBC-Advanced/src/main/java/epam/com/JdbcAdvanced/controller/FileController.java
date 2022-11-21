package epam.com.JdbcAdvanced.controller;

import epam.com.JdbcAdvanced.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping("/{id}")
    public void getFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        fileService.getFile(id, response);
    }

    @PostMapping
    public String storeFile(@RequestParam(name = "file") MultipartFile file) {
        return fileService.storeFile(file);
    }
}
