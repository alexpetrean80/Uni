package group2.cms.service;

import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.ConferenceRepository;
import group2.cms.service.DTO.Conference.ConferenceDTO;
import group2.cms.service.DTO.Conference.ConferenceDTOConverter;
import group2.cms.service.DTO.Conference.ConferencesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceDTOConverter converter;

    @Autowired
    private ConferenceRepository repository;

    public ConferenceDTO addConference(ConferenceDTO conferenceDTO) {
        var newConference = repository.save(converter.dtoToEntity(conferenceDTO));
        return converter.entityToDto(newConference);
    }

    public void deleteConference(Long conferenceID) {
        try{
            repository.deleteById(conferenceID);
        }catch (EmptyResultDataAccessException | IllegalArgumentException e){
            throw new InvalidIDException("Invalid conferenceID: " + conferenceID);
        }
    }

    public ConferenceDTO updateConference(ConferenceDTO conferenceDTO) {
        var conferenceData = converter.dtoToEntity(conferenceDTO);
        var id = conferenceData.getId();
        var conf = repository.findById(id).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID:" + id)
        );
        var name = conferenceData.getName();
        var edition = conferenceData.getEdition();
        var startDate = conferenceData.getStartDate();
        var endDate = conferenceData.getEndDate();

        if(name != null) conf.setName(name);
        if(edition != null) conf.setEdition(edition);
        if(startDate != null) conf.setStartDate(startDate);
        if(endDate != null) conf.setEndDate(endDate);

        return converter.entityToDto(repository.save(conf));
    }

    public ConferenceDTO getById(Long conferenceID)
    {
        var conference = repository.findById(conferenceID).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID: " + conferenceID)
        );
        return converter.entityToDto(conference);
    }

    public ConferencesDTO getAll() {
        return converter.entitiesToDTO(repository.findAll());
    }
}
