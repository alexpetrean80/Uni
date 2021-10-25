package group2.cms.service;

import group2.cms.domain.PaperFile;
import group2.cms.exceptions.BackendException;
import group2.cms.repository.PaperFileRepository;
import group2.cms.service.DTO.PaperFile.PaperFileDTO;
import group2.cms.service.DTO.PaperFile.PaperFileDTOConverter;
import group2.cms.service.DTO.PaperFile.PaperFilesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaperFileService {
    @Autowired
    private PaperFileRepository repository;

    @Autowired
    private PaperFileDTOConverter converter;

    PaperFileDTO add(PaperFileDTO dto) throws IOException {
        var file = repository.save(converter.dtoToEntity(dto));
        return converter.entityToDto(file);
    }

    void delete(Long id) {
        repository.deleteById(id);
    }

//    PaperFileDTO getById(Long id) {
//        var file = repository.findById(id)
//                .orElseThrow(() -> new BackendException("File not found"));
//        return converter.entityToDto(file);
//    }
//
//    PaperFilesDTO getAll() {
//        return converter.entitiesToDTO(repository.findAll());
//    }
}
