package group2.cms.service.DTO.PaperFile;

import group2.cms.domain.PaperFile;
import group2.cms.service.DTO.DTOConverter;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@Component
public class PaperFileDTOConverter implements DTOConverter<PaperFile, PaperFileDTO> {
    @SneakyThrows
    @Override
    public PaperFile dtoToEntity(PaperFileDTO dto) {
        var paperFile = new PaperFile(
                StringUtils.cleanPath(Objects.requireNonNull(dto.getFile().getOriginalFilename())),
                dto.getFile().getContentType(),
                dto.getFile().getSize(),
                dto.getFile().getBytes()
        );
        if (dto.getId() != null)
            paperFile.setId(dto.getId());
        return paperFile;
    }

    @SneakyThrows
    @Override
    public PaperFileDTO entityToDto(PaperFile paperFile) {
       /* var fileFactory = new DiskFileItemFactory();
        var f = File.createTempFile(paperFile.getName(), "");
        var item = new DiskFileItem(
                "",
                paperFile.getType(),
                true,
                paperFile.getName(),
                Math.toIntExact(paperFile.getSize()),
                f
        );
        var file = new CommonsMultipartFile(item);
        f.deleteOnExit();
        return PaperFileDTO.builder()
                .id(paperFile.getId())
                .file(
                        new (
                paperFile.getName(),
                paperFile.getData()
                    )
                )
                .build();*/
        return null;
    }

    @Override
    public PaperFilesDTO entitiesToDTO(Collection<PaperFile> entities) {
        return null;
    }
}
