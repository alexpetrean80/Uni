package group2.cms.service.DTO.Author;

import group2.cms.domain.Author;
import group2.cms.domain.CMSUser;
import group2.cms.domain.Paper;
import group2.cms.service.DTO.DTOConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthorDTOConverter implements DTOConverter<Author, AuthorDTO> {

    /// Paper must be retrieved (if present in the database) and set manually with author.setPaper() by the service
    @Override
    public Author dtoToEntity(AuthorDTO dto) {
        var user = new CMSUser(
                dto.getFullName(),
                dto.getEmail(),
                dto.getUsername(),
                dto.getPassword()
        );
        if(dto.getUserID() != null)
             user.setId(dto.getUserID());
        var paper = new Paper();
        paper.setId(dto.getPaperID());
        return new Author(user, paper);
    }

    @Override
    public AuthorDTO entityToDto(Author author) {
        return AuthorDTO.builder()
                .userID(author.getId())
                .fullName(author.getFullName())
                .username(author.getUsername())
                .email(author.getEmail())
                .password(author.getPassword())
                .paperID(author.getPaper().getId())
                .build();
    }

    @Override
    public AuthorsDTO entitiesToDTO(Collection<Author> authors) {
        var authorsDTO = new AuthorsDTO();
        authors.stream()
                .map(this::entityToDto)
                .forEach(authorsDTO::addDTO);
        return authorsDTO;
    }


}
