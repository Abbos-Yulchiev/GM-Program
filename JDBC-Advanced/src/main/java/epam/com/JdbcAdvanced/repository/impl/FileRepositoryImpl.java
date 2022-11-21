package epam.com.JdbcAdvanced.repository.impl;

import epam.com.JdbcAdvanced.exceptions.NotFoundException;
import epam.com.JdbcAdvanced.model.FileData;
import epam.com.JdbcAdvanced.repository.FileRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private final JdbcTemplate jdbcTemplate;

    public FileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String storeFile(MultipartFile file) {


        Long id = jdbcTemplate.queryForObject("SELECT id FROM file_storage ORDER BY id DESC LIMIT 1", Long.class);
        String sql = "INSERT INTO file_storage (id, filename, content, bytes, extention) values (?, ?, ?, ?, ?)";
        if (id == null) {
            throw new NotFoundException("Id is not right");
        }
        int result = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id + 1);
            statement.setString(2, file.getName());
            statement.setString(3, file.getContentType());
            ByteArrayInputStream inputStream;
            try {
                inputStream = new ByteArrayInputStream(file.getBytes());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            statement.setBinaryStream(4, inputStream);
            statement.setString(5, getExtension(file));
            return statement;
        });
        return "New file stored result:[" + result + "]";
    }

    @Override
    public void getFile(Long id, HttpServletResponse response) throws IOException {

        response.flushBuffer();
        List<FileData> file = jdbcTemplate.query("SELECT * FROM file_storage where id =?",
                new Object[]{id}, (resultSet, i) -> {
                    try {
                        return toReport(resultSet);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


        FileData fileData = file.get(0);

        File tempFile = File.createTempFile(fileData.getFilename(), "." + fileData.getExtention() , new File("D:\\Projects\\GM-Program\\JDBC-Advanced\\src\\main\\resources"));

        FileOutputStream os = new FileOutputStream(tempFile);
        os.write(fileData.getBytes());
        os.flush();
                                      os.close();
    InputStream is = new FileInputStream(tempFile);
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
}

    private FileData toReport(ResultSet resultSet) throws SQLException, IOException {
        FileData file = new FileData();
        file.setId(resultSet.getLong("id"));
        file.setFilename(resultSet.getString("filename"));
        file.setContent(resultSet.getString("content"));
        file.setExtention(resultSet.getString("extention"));

        InputStream fileData = resultSet.getBinaryStream("bytes");
        byte[] bytes = new byte[fileData.available()];
        file.setBytes(bytes);
        return file;
    }

    public String getExtension(MultipartFile file) {
        // convert the file name into string
        String fileName = file.getOriginalFilename();

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index + 1);
        }
        throw new IllegalStateException();
    }
}