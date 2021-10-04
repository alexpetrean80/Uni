package group2.cms.service;

import group2.cms.domain.Author;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.AuthorRepository;
import group2.cms.repository.PaperRepository;
import group2.cms.repository.UserRepository;
import group2.cms.service.DTO.Author.AuthorDTO;
import group2.cms.service.DTO.Author.AuthorDTOConverter;
import group2.cms.service.DTO.Author.AuthorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class AuthorService {

    @Autowired
    private AuthorDTOConverter converter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDTO addAuthor(AuthorDTO authorDTO){
        var authorData = converter.dtoToEntity(authorDTO);
        var userID = authorData.getId();
        var paperID = authorData.getPaper().getId();
        var paperUploader = userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );
        var uploadedPaper = paperRepository.findById(paperID).orElseThrow(
                () -> new InvalidIDException("Invalid Paper ID: " + paperID)
        );

        var addedAuthor =  authorRepository.save(new Author(paperUploader, uploadedPaper));
        return converter.entityToDto(addedAuthor);
    }

    public void deleteAuthor(Long authorID)
    {
        try{
            authorRepository.deleteById(authorID);
        }catch(IllegalArgumentException | EmptyResultDataAccessException e){
            throw new InvalidIDException("Invalid Author ID: " + authorID);
        }
    }

    public AuthorsDTO getAllAuthors(){
        return converter.entitiesToDTO(authorRepository.findAll());
    }

    public AuthorsDTO getAuthorsOfPaper(AuthorDTO authorDTO){
        var paperID = converter.dtoToEntity(authorDTO).getPaper().getId();
        var paper = paperRepository.findById(paperID).orElseThrow(
                () -> new InvalidIDException("Invalid Paper ID: " + paperID)
        );
        return converter.entitiesToDTO(authorRepository.findAuthorsByPaper(paper));
    }

    public AuthorDTO getAuthorByID(Long authorID){
        var author =  authorRepository.findById(authorID).orElseThrow(
                () -> new InvalidIDException("Invalid Author ID: " + authorID)
        );
        return converter.entityToDto(author);
    }

    public AuthorDTO getAuthorByEmail(String email){
        var author = authorRepository.findAuthorByEmail(email).orElseThrow(
                () -> new InvalidIDException("Invalid Author email address: " + email)
        );
        return converter.entityToDto(author);
    }

    public AuthorDTO getAuthorByUsername(String username){
        var author = authorRepository.findAuthorByUsername(username).orElseThrow(
                () -> new InvalidIDException("Invalid Author Username: " + username)
        );
        return converter.entityToDto(author);
    }
}
