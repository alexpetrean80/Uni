package group2.cms.service.DTO.PaperFile;

import group2.cms.domain.PaperFile;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
@Getter
public class PaperFileDTO extends EntityDTO<PaperFile> {

    private Long id;
    private MultipartFile file;


}
