package group2.cms.service;


import group2.cms.exceptions.CredentialsAlreadyInUseException;
import group2.cms.exceptions.InvalidCredentialsException;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.UserRepository;
import group2.cms.service.DTO.CMSUser.CMSUserDTO;
import group2.cms.service.DTO.CMSUser.CMSUserDTOConverter;
import group2.cms.service.DTO.CMSUser.CMSUsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CMSUserService {

    @Autowired
    private CMSUserDTOConverter converter;

    @Autowired
    private UserRepository userRepository;

    private void throwExceptionIfCredentialsAlreadyInUse(String username, String email) {
        var errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(
                userRepository.existsByUsername(username) ? "Username" + username + "already in use.\n" : ""
        ).append(
                userRepository.existsByEmail(email) ? "Email address" + email + "already in use.\n" : ""
        );

        if (errorMessageBuilder.length() != 0)
            throw new CredentialsAlreadyInUseException(errorMessageBuilder.toString());

    }


    public CMSUserDTO addUser(CMSUserDTO userDTO) {
        var newUser = converter.dtoToEntity(userDTO);
        throwExceptionIfCredentialsAlreadyInUse(newUser.getUsername(), newUser.getEmail());
        return converter.entityToDto(userRepository.save(newUser));
    }

    public CMSUserDTO updateUser(CMSUserDTO userDTO){
        var updatedUserData = converter.dtoToEntity(userDTO);
        var userID = updatedUserData.getId();
        var user = userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );

        throwExceptionIfCredentialsAlreadyInUse(
                Optional.ofNullable(updatedUserData.getUsername()).orElse(""),
                Optional.ofNullable(updatedUserData.getEmail()).orElse(""));

        if(updatedUserData.getFullName() != null)
            user.setFullName(updatedUserData.getFullName());

        if(updatedUserData.getEmail() != null)
            user.setEmail(updatedUserData.getEmail());

        if(updatedUserData.getUsername() != null)
            user.setUsername(updatedUserData.getUsername());

        if(updatedUserData.getPassword() != null)
            user.setPassword(updatedUserData.getPassword());


        return converter.entityToDto(userRepository.save(user));
    }

    public void deleteUser(Long userID){
        try{
            userRepository.deleteById(userID);
        }catch(EmptyResultDataAccessException | IllegalArgumentException e){
            throw new InvalidIDException("Invalid User ID: " + userID);
        }
    }

    public CMSUserDTO getUserByID(Long userID){
        var user = userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );
        return converter.entityToDto(user);
    }

    public CMSUserDTO getUserByUsername(String username){
        var user= userRepository.findByUsername(username).orElseThrow(
                () -> new InvalidCredentialsException("There is no user with the username: " + username)
        );
        return converter.entityToDto(user);
    }

    public CMSUserDTO getUserByEmail(String email){
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new InvalidCredentialsException("There is no user with the given email address: " + email)
        );
        return converter.entityToDto(user);
    }

    public CMSUsersDTO getAllUsers(){
      return converter.entitiesToDTO(userRepository.findAll());
    }

}
