package epam.com.JdbcAdvanced.service.impl;

import epam.com.JdbcAdvanced.repository.FileRepository;
import epam.com.JdbcAdvanced.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void getFile(Long id, HttpServletResponse response) throws IOException {
        fileRepository.getFile(id, response);
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        return fileRepository.storeFile(file);
    }
}
