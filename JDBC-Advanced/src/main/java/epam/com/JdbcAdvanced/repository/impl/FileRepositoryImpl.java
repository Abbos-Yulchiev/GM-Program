package epam.com.JdbcAdvanced.repository.impl;

import epam.com.JdbcAdvanced.model.FileData;
import epam.com.JdbcAdvanced.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@PropertySource("application.properties")
@Repository
public class FileRepositoryImpl implements FileRepository {

    private final JdbcTemplate jdbcTemplate;

    @Value("${file.loader.path}")
    private String FILEPATH;

    public FileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void getFile(Long id, HttpServletResponse response) throws IOException {

        response.flushBuffer();
        FileData fileData = jdbcTemplate.queryForObject("SELECT * FROM file_storage where id =?", new Object[]{id}, (resultSet, i) -> {
            try {
                return toReport(resultSet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        String path = Objects.requireNonNull(fileData).getFilePath();
        byte[] bytes = Files.readAllBytes(new File(path).toPath());

        response.setHeader("Content-Disposition", "filename: \"" + fileData.getFilename());
        response.setContentType(fileData.getExtension());
        FileCopyUtils.copy(bytes, response.getOutputStream());
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {

        String sql = "INSERT INTO file_storage (filename, content, file_path) VALUES ('" + file.getOriginalFilename() + "', '" + file.getContentType() + "', '" + FILEPATH + file.getOriginalFilename() + "')";
        jdbcTemplate.execute(sql);

        try (FileOutputStream outputStream = new FileOutputStream(FILEPATH + file.getOriginalFilename())) {
            outputStream.write(file.getBytes());
        }
        return "File uploaded successfully";
    }

    private FileData toReport(ResultSet resultSet) throws SQLException, IOException {
        FileData file = new FileData();
        file.setId(resultSet.getLong("id"));
        file.setFilename(resultSet.getString("filename"));
        file.setExtension(resultSet.getString("extension"));
        file.setFilePath(resultSet.getString("file_path"));
        return file;
    }
}