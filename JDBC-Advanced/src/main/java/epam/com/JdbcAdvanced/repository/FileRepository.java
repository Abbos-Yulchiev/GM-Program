package epam.com.JdbcAdvanced.repository;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileRepository {

    void getFile(Long id, HttpServletResponse response) throws IOException;

    String storeFile(MultipartFile file);
}
