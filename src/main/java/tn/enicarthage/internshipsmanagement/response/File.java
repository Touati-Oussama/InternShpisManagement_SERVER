package tn.enicarthage.internshipsmanagement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private String idFile;
    private String fileDownloadUri;
    private String rapport;
}
