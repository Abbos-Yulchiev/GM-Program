package epam.com.JdbcAdvanced.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileService {

    void getFile(Long id, HttpServletResponse response) throws IOException;

    String storeFile(MultipartFile file) throws IOException;
}
