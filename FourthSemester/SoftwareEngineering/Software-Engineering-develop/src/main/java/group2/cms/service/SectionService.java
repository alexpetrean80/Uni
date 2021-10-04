package group2.cms.service;

import group2.cms.domain.Section;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.PCMemberRepository;
import group2.cms.repository.SectionRepository;
import group2.cms.service.DTO.Section.SectionDTO;
import group2.cms.service.DTO.Section.SectionDTOConverter;
import group2.cms.service.DTO.Section.SectionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;
    private PCMemberRepository pcMemberRepository;
    private SectionDTOConverter converter;

    public SectionDTO addSection(SectionDTO dto) {
        var section = converter.dtoToEntity(dto);
        section.setSessionChair(
                pcMemberRepository
                        .findById(section.getSessionChair().getId())
                        .orElseThrow(
                                () -> new InvalidIDException("Invalid member ID: " + section.getSessionChair().getId())
                        )
        );
        return converter.entityToDto(sectionRepository.save(section));
    }

    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }

    public Section updateSection(Long id, String room, Integer capacity, LocalDate startDate, LocalDate endDate, String theme, Long chairId) {
        var section = sectionRepository.findById(id).orElseThrow(() -> new InvalidIDException("Invalid section ID: " + id));
        section.setRoom(room);
        section.setCapacity(capacity);
        section.setStartDate(startDate);
        section.setEndDate(endDate);
        section.setTheme(theme);
        section.setSessionChair(
                pcMemberRepository
                        .findById(chairId)
                        .orElseThrow(
                                () -> new InvalidIDException("Invalid member ID: " + chairId)
                        )
        );
        sectionRepository.deleteById(id);
        return sectionRepository.save(section);
    }

    public SectionDTO findById(Long id) {
        return converter.entityToDto(sectionRepository
                .findById(id)
                .orElseThrow(() -> new InvalidIDException("Invalid section id: " + id)));
    }

    public SectionsDTO getAll() {
        var dto = new SectionsDTO();
        var sections = sectionRepository.findAll();
        for (Section section : sections) {
            dto.addDTOS(
                    converter.entityToDto(section));
        }
        return dto;
    }
}
