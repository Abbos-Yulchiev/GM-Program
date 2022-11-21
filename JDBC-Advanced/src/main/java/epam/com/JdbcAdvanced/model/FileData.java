package epam.com.JdbcAdvanced.model;

import lombok.Data;

@Data
public class FileData {

    private long id;

    private String filename;

    private String content;

    private byte[] bytes;

    private String extention;
}
