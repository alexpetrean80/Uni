package group2.cms.service;


import group2.cms.domain.Keyword;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    public Keyword addKeyword(String keyword){
        return keywordRepository.save(new Keyword(keyword));
    }

    public void deleteKeyword(Long keywordId){
        try{
            keywordRepository.deleteById(keywordId);
        }catch (EmptyResultDataAccessException | IllegalArgumentException e){
            throw new InvalidIDException("Invalid Keyword ID: " + keywordId);
        }
    }

    public List<Keyword> getAllKeywords(){
        return keywordRepository.findAll();
    }

    public Keyword getKeywordById(Long keywordId){
        return keywordRepository.findById(keywordId)
                .orElseThrow(() -> new InvalidIDException("invalid keywordId " + keywordId));
    }

    public Keyword getKeyword(String keyword){
        return keywordRepository.findByKeyword(keyword)
                .orElseThrow(() -> new InvalidIDException("invalid keyword " + keyword));
    }
}
