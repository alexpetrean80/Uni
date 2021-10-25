package group2.cms.service.DTO.Paper;

import group2.cms.domain.Author;
import group2.cms.domain.Keyword;
import group2.cms.domain.Paper;
import group2.cms.service.DTO.DTOConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PaperDTOConverter implements DTOConverter<Paper, PaperDTO> {
    @Override
    public Paper dtoToEntity(PaperDTO dto) {
        var authors = dto.getAuthorIDs().stream().map((authorId) -> {
            var authorData = new Author();
            authorData.setId(authorId);
            return authorData;
        }).collect(Collectors.toList());

        var keywords = dto.getKeywords().stream().map((keywordString) -> {
            var keywordData = new Keyword(keywordString);
            keywordData.setId(0L);
            return keywordData;
        }).collect(Collectors.toSet());
        var paper = new Paper(
                dto.getTitle(),
                dto.getPaperAbstract(),
                authors,
                keywords
        );
        if(dto.getId() != null)
            paper.setId(dto.getId());
        return paper;
    }

    @Override
    public PaperDTO entityToDto(Paper paper) {
        return PaperDTO.builder()
                .id(paper.getId())
                .title(paper.getTitle())
                .paperAbstract(paper.getPaperAbstract())
                .authorIDs(
                        paper.getAuthorList().stream()
                                .map(Author::getId)
                                .collect(Collectors.toList())
                )
                .keywords(
                        paper.getKeywordSet().stream()
                                .map(Keyword::getKeyword)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public PapersDTO entitiesToDTO(Collection<Paper> papers) {
        var papersDTO = new PapersDTO();

        papers.stream()
                .map(this::entityToDto)
                .forEach(papersDTO::addDTO);

        return papersDTO;
    }
}
