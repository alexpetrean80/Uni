package group2.cms.service;

import group2.cms.domain.PCMember;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.PCMemberRepository;
import group2.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PCMemberService {

    @Autowired
    private PCMemberRepository pcMemberRepository;

    @Autowired
    private UserRepository userRepository;

    public PCMember addPCMember(Long userID, String affiliation, String website){
        var user = userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );
        return pcMemberRepository.save(new PCMember(
                user,
                affiliation,
                website
        ));
    }

    public void deletePCMember(Long pcMemberID){
        try{
            pcMemberRepository.deleteById(pcMemberID);
        }catch(EmptyResultDataAccessException | IllegalArgumentException e){
            throw new InvalidIDException("Invalid PC Member ID: " + pcMemberID);
        }
    }

    public PCMember updatePCMember(Long pcMemberID, Optional<String> newAffiliation, Optional<String> newWebsite){
        var pcMember = pcMemberRepository.findById(pcMemberID).orElseThrow(
                () -> new InvalidIDException("Invalid PCMember ID: " + pcMemberID)
        );
        newAffiliation.ifPresent(pcMember::setAffiliation);
        newWebsite.ifPresent(pcMember::setWebPage);
        return pcMemberRepository.save(pcMember);
    }

    public List<PCMember> getAllPCMembers(){
        return pcMemberRepository.findAll();
    }

    public List<PCMember> getPCMembersByAffiliation(String affiliation){
        return pcMemberRepository.findAllByAffiliation(affiliation);
    }

    public PCMember getPCMemberByID(Long id){
        return pcMemberRepository.findById(id).orElseThrow(
                () -> new InvalidIDException("Invalid PCMember ID: " + id)
        );
    }
}
