package group2.cms.service;

import group2.cms.domain.Listener;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.ListenerRepository;
import group2.cms.repository.SectionRepository;
import group2.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListenerService {

    @Autowired
    private ListenerRepository listenerRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private UserRepository userRepository;

    public Listener addListener(Long userID){
        var user = userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );
        return listenerRepository.save(new Listener(user));
    }

    public void deleteListener(Long listenerID){
        try{
            listenerRepository.deleteById(listenerID);
        }catch (IllegalArgumentException | EmptyResultDataAccessException e){
            throw new InvalidIDException("Invalid listenerID: " + listenerID);
        }
    }

    public Listener addNewSection(Long listenerID, Long sectionID){
        var newSection = sectionRepository.findById(sectionID).orElseThrow(
                () -> new InvalidIDException("Invalid Section ID: " + sectionID));
        var listener = listenerRepository.findById(listenerID).orElseThrow(
                () -> new InvalidIDException("Invalid Listener ID: " + listenerID)
        );
        listener.addSection(newSection);
        return listenerRepository.save(listener);
    }

    public List<Listener> getAllListeners(){
        return listenerRepository.findAll();
    }

    public Listener getListenerByID(Long listenerID){
        return listenerRepository.findById(listenerID).orElseThrow(
                () -> new InvalidIDException("Invalid Listener ID: " + listenerID)
        );
    }

    public List<Listener> getAllListenersBySection(Long sectionID){
        var section = sectionRepository.findById(sectionID).orElseThrow(
                () -> new InvalidIDException("Invalid Section ID: " + sectionID));
        return listenerRepository.findAll()
                                .stream()
                                .filter(listener -> listener.getSections().contains(section))
                                .collect(Collectors.toList());
    }

}
