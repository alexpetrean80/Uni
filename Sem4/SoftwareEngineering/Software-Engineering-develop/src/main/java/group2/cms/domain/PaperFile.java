package group2.cms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class PaperFile extends BaseEntity<Long>{
    public static final String pdfMimeType = "text/pdf";
    public static final String pptMimeType = "application/vnd.ms-powerpoint";
    public static final String pptxMimeType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";


    private String name;
    private String type;
    private Long size;

    @Lob
    private byte[] data;
}
