package group2.cms.service;

import group2.cms.domain.Paper;
import group2.cms.domain.Presentation;
import group2.cms.domain.Section;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class PresentationService {

    @Autowired
    private PresentationRepository presentationRepository;

    public Presentation addPresentation(Long id, Paper paper, Section section, LocalDate startDate, LocalDate endDate, File presentation){
        return presentationRepository.save(new Presentation(id, paper, section, startDate, endDate, presentation));
    }

    public void deletePresentation(Long presentationID){
        try{
            presentationRepository.deleteById(presentationID);
        }catch(IllegalArgumentException | EmptyResultDataAccessException e){
            throw new InvalidIDException("Invalid Presentation Exception: " + presentationID);
        }
    }

    public List<Presentation> getAllPresentations(){
        return presentationRepository.findAll();
    }

    public Presentation getPresentationByID(Long presentationID){
        return presentationRepository.findById(presentationID).orElseThrow(
                () -> new InvalidIDException("Invalid Presentation ID: " + presentationID)
        );
    }
}
