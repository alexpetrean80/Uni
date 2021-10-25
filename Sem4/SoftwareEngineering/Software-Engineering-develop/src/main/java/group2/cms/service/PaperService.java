package group2.cms.service;

import group2.cms.domain.Paper;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.AuthorRepository;
import group2.cms.repository.KeywordRepository;
import group2.cms.repository.PaperRepository;
import group2.cms.service.DTO.Paper.PaperDTO;
import group2.cms.service.DTO.Paper.PaperDTOConverter;
import group2.cms.service.DTO.Paper.PapersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    private PaperDTOConverter converter;

    public Paper addPaper(PaperDTO paperDTO){
        var paperData = converter.dtoToEntity(paperDTO);
        var title = paperData.getTitle();
        var paperAbstract = paperData.getPaperAbstract();
        var authors = paperData.getAuthorList().stream()
                .map((authorData) ->
                     authorRepository.findById(authorData.getId()).orElseThrow(
                            () -> new InvalidIDException("Invalid Author ID in Paper DTO: " + authorData.getId())
                    )).collect(Collectors.toList());

        var keywords = paperData.getKeywordSet().stream()
                .map((keyword) ->
                    keywordRepository.findByKeyword(keyword.getKeyword()).orElseThrow(
                            () -> new InvalidIDException("Invaid keyword: " + keyword.getKeyword())
                    )).collect(Collectors.toSet());

        return paperRepository.save(new Paper(title, paperAbstract, authors, keywords));
    }

    public void deletePaper(Long paperId){
        try{
            paperRepository.deleteById(paperId);
        }catch(IllegalArgumentException | EmptyResultDataAccessException e){
            throw new InvalidIDException("Invalid Paper ID: " + paperId);
        }
    }

    public PapersDTO getAllPapers(){
        return converter.entitiesToDTO(paperRepository.findAll());
    }

    public PaperDTO getPaperById(Long paperId){
        var paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new InvalidIDException("Invalid paperId " + paperId));
        return converter.entityToDto(paper);
    }

    public PaperDTO getPaperByTitle(String title){
        var paper = paperRepository.findPaperByTitle(title)
                .orElseThrow(() -> new InvalidIDException("Invalid title " + title));
        return converter.entityToDto(paper);
    }
}
