package epam.com.JdbcAdvanced.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {

    private long id;

    private String filename;

    private String extension;

    private String filePath;
}
